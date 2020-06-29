package nooblong.servlet.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import nooblong.dao.impl.BookDaoImpl;
import nooblong.domain.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet("/IndexBookServlet")
public class IndexBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("index servlet");
        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        //首页随机展示10本书
        int num = 10;
        int realNum = bookDaoImpl.bookNum();
        if (realNum < num)
            num = realNum;

        //10本书的列表
        List<Book> bookList = new ArrayList<>();
        System.out.println(num + " run num");
        List<Integer> randomList = randomList(10, realNum);
        for (int i = 0; i < num; i++) {
            int id = randomList.get(i);
            Book bookById = bookDaoImpl.getBookById(id);
            bookList.add(bookById);

        }

        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(response.getOutputStream(), bookList);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * 生成随机数
     * @param nums 个数
     * @param max 最大
     * @return
     */
    private List<Integer> randomList(int nums, int max){
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        while(list.size() <= nums){
            int num = random.nextInt(max)+1;
            if(!list.contains(num)){
                list.add(num);
            }
        }
        System.out.println(list);
        return list;
    }
}
