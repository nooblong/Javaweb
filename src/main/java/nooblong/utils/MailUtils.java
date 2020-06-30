package nooblong.utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;


public class MailUtils {

    static {
        Properties properties = new Properties();
        try {
            properties.load(MailUtils.class.getClassLoader().getResourceAsStream("mail.properties"));
            USER = properties.getProperty("user");
            PASSWORD = properties.getProperty("password");
            FROM = properties.getProperty("from");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String USER;// 发件人称号，同邮箱地址
    private static String PASSWORD;// 发件人邮箱客户端的授权码
    private static String FROM;//发件人的邮箱地址

    public static boolean sendMail(String to, String text, String title) {
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.163.com"); // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
        props.put("mail.smtp.host", "smtp.163.com"); // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
        props.put("mail.smtp.auth", "true"); // 用刚刚设置好的props对象构建一个session
        Session session = Session.getDefaultInstance(props); // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
        // 用（你可以在控制台（console)上看到发送邮件的过程）
        session.setDebug(true); // 用session为参数定义消息对象
        MimeMessage message = new MimeMessage(session); // 加载发件人地址
        try {
            message.setFrom(new InternetAddress(FROM));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); // 加载收件人地址
            message.setSubject(title); // 加载标题
            Multipart multipart = new MimeMultipart(); // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            BodyPart contentPart = new MimeBodyPart(); // 设置邮件的文本内容
            contentPart.setContent(text, "text/html;charset=utf-8");
            multipart.addBodyPart(contentPart);
            message.setContent(multipart);
            message.saveChanges(); // 保存变化
            Transport transport = session.getTransport("smtp"); // 连接服务器的邮箱
            transport.connect("smtp.163.com", USER, PASSWORD); // 把邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

//    public static boolean sendMail(String to, String text, String title){
//        try {
//            final Properties props = new Properties();
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.host", "smtp.163.com");
//
//            props.put("mail.user", USER);
//            props.put("mail.password", PASSWORD);
//
//            Authenticator authenticator = new Authenticator() {
//                @Override
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    String username = props.getProperty("mail,user");
//                    String password = props.getProperty("mail,password");
//                    return new PasswordAuthentication(username, password);
//                }
//            };
//            Session mailSession = Session.getInstance(props,authenticator);
//            MimeMessage message = new MimeMessage(mailSession);
//            //设置发件人
//            String username = props.getProperty("mail.user");
//            InternetAddress form = new InternetAddress(username);
//            message.setFrom(form);
//
//            //设置收件人
//            InternetAddress toAddress = new InternetAddress(to);
//            message.setRecipient(Message.RecipientType.TO, toAddress);
//
//            //标题
//            message.setSubject(title);
//            //内容
//            message.setContent(text,"text/html;charset=utf-8");
//            //发送
//            Transport.send(message);
//            return true;
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    public static void main(String[] args) {
        MailUtils.sendMail("18128966990@163.com", "test2", "nihao");
    }
}
