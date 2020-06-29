package nooblong.dao.impl;

import nooblong.dao.BookDao;
import nooblong.domain.Book;
import nooblong.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

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
}
