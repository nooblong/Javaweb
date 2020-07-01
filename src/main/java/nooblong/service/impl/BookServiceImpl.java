package nooblong.service.impl;

import nooblong.dao.BookDao;
import nooblong.dao.impl.BookDaoImpl;
import nooblong.domain.Book;
import nooblong.service.BookService;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDaoImpl();

    @Override
    public int getBookNum(String type){
        return bookDao.bookNum(type);
    }

    @Override
    public List<Book> getBooks(int num){
        return null;
    }

    @Override
    public List<Book> getBooks(String type){
        return bookDao.bookList(type);
    }

    @Override
    public Book getBookById(int id) {
        return bookDao.getBookById(id);
    }
}
