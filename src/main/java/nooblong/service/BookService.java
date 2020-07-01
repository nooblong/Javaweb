package nooblong.service;

import nooblong.domain.Book;

import java.util.List;

public interface BookService {
    /**
     * 获取这种类型的书有几本
     * @param type like A
     * @return 数量
     */
    int getBookNum(String type);

    /**
     * 未使用
     * @param num num
     * @return list
     */
    List<Book> getBooks(int num);

    /**
     * 获取一个种类的所有书
     * @param type like A
     * @return List
     */
    List<Book> getBooks(String type);

    /**
     * 根据id获取书
     * @param id
     * @return
     */
    Book getBookById(int id);
}
