package com.zy.search.service;

import com.zy.search.AbstractSpringBootTest;
import com.zy.search.beans.Book;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by zy.
 */
public class BookIndexTest extends AbstractSpringBootTest{
    @Autowired
    private BookIndex bookIndex;

    @Test
    public void indexBooks(){
        List<Book> list = new ArrayList<>();
        for (int i=0;i<10;i++) {
            Book book = new Book();
            book.setName("name"+i);
            book.setCtime(System.currentTimeMillis());
            book.setId("book"+i);
            book.setPrice(12.3+i);
            list.add(book);
        }
        bookIndex.index(list);
    }
}
