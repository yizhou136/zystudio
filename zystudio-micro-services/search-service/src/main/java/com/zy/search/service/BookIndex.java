package com.zy.search.service;

import com.zy.search.beans.Book;

import java.util.List;

/**
 * @author by zy.
 */

public interface BookIndex {

    void index(List<Book> list);

    List<Book> query(String name);

}
