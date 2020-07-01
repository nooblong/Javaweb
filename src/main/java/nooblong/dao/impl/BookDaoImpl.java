package nooblong.dao.impl;

import nooblong.dao.BookDao;
import nooblong.domain.Book;
import nooblong.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class BookDaoImpl implements BookDao {
    //声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Book getBookById(int id){
        //language=MySQL
        String sql = "select * from bookinfo where id = ?";
        Book book = template.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class),id);
        return book;
    }

    @Override
    public int bookNum(){
        //language=MySQL
        String sql = "select count(*) from bookinfo";
        int num = template.queryForObject(sql, Integer.class);
        return num;
    }

    @Override
    public int bookNum(String type){
        //language=MySQL
        String sql = "select count(*) from bookinfo where type = ?";
        int num = template.queryForObject(sql, Integer.class, type);
        return num;
    }

    @Override
    public List<Book> bookList(String type){
        //language=MySQL
        String sql = "select * from bookinfo where type = ?";
        RowMapper<Book> rowMapper = new BeanPropertyRowMapper<>(Book.class);
        return template.query(sql, rowMapper, type);
    }

    @Override
    public boolean addBook(Book book){
        //language=MySQL
        String sql = "insert into bookinfo (name, isbn, publisher, writer, introduce, price, pDate, type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, book.getName(),book.getIsbn(), book.getPublisher(),
                book.getWriter(), book.getIntroduce(), book.getPrice(), book.getpDate(), book.getType());
        System.out.println("save book successfully");
        return true;
    }

    @Override
    public List<Book> allBook(){
        //language=MySQL
        String sql = "select * from bookinfo";
        RowMapper<Book> rowMapper = new BeanPropertyRowMapper<>(Book.class);
        return template.query(sql, rowMapper);
    }
}
