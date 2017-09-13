package com.valerastudy.BookGenerator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BookGen {
    private int bookId;
    private String bookName;

    public BookGen(int bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public static void main(String[] args) {
        String hibernateCfgXml = "com\\valerastudy\\BookGenerator\\hibernate.cfg.xml";

        SessionFactory sessionFactory = new Configuration().configure(hibernateCfgXml).buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        for (int i = 1; i <= 10; i++) {
            session.save(new BookGen(i, "Name " + i));
        }

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}
