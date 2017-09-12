package com.valerastudy.example1;

/*
* Exemplu 1
* Hibernate curat cu H2 DB
*/

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Example1MainClass {

    public static String hibernateCfgXml = "com\\valerastudy\\example1\\hibernate.cfg.xml";

    public static void main(String[] args) {
        BookEx1 book = new BookEx1();
        book.setBookId(1);
        book.setBookName("Book 1");

        //open SessionFactory
        SessionFactory sessionFactory = new Configuration().configure(hibernateCfgXml).buildSessionFactory();

        //open Session
        Session session = sessionFactory.openSession();

        //open begin Transaction
        session.beginTransaction();

        session.save(book);

        //open commit Transaction
        session.getTransaction().commit();

        //close Session
        session.close();

        // acum citim din BD

        book.setBookId(2);
        book.setBookName("Book 2");
        System.out.println("Book reseted before loading from DB: [id = " + book.getBookId() + ", name = " + book.getBookName() + "]");
        session = sessionFactory.openSession();
        session.beginTransaction();
        book = session.get(BookEx1.class, 1);
        session.getTransaction().commit();
        session.close();

        System.out.println("Book reseted after loading from DB: [id = " + book.getBookId() + ", name = " + book.getBookName() + "]");

        //close SessionFactory
        sessionFactory.close();
    }
}
