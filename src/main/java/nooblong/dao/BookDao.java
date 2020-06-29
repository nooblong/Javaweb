package nooblong.dao;

import nooblong.domain.Book;

public interface BookDao {
    public Book getBookById(int id);
    public int bookNum();
}
