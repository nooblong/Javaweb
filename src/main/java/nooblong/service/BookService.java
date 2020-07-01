package nooblong.service;

import nooblong.domain.Book;

import java.util.List;

public interface BookService {
    int getBookNum(String type);

    List<Book> getBooks(int num);

    List<Book> getBooks(String type);
}
