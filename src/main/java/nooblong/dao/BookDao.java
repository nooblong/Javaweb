package nooblong.dao;

import nooblong.domain.Book;

import java.util.List;

public interface BookDao {
    public Book getBookById(int id);
    public int bookNum();
    public int bookNum(String type);

    List<Book> bookList(String type);

    boolean addBook(Book book);

    List<Book> allBook();
}
