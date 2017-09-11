package com.valerastudy.example1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Example1MainClass {
    public static void main(String[] args) {
        Book book = new Book();
        book.setBookId(1);
        book.setBookName("Book 1");

        //open SessionFactory
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        //open Session
        Session session = sessionFactory.openSession();

        //open begin Transaction
        session.beginTransaction();

        session.save(book);

        //open commit Transaction
        session.getTransaction().commit();

        //close Session
        session.close();

        //close SessionFactory
        sessionFactory.close();
    }
}
