package com.example.liuhe.dao;

public class BookMarkDAOFactory {
    public static BookMarkDAOProxy getBookMark(){
        return  new BookMarkDAOProxy();
    }
}
