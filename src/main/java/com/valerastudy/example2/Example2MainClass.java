package com.valerastudy.example2;

import com.valerastudy.example1.BookEx1;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Example2MainClass {
    public static void main(String[] args) {
        BookEx2 book = new BookEx2();
        book.setBookName("Book 1");
        book.setBookCategory("Category 1");

        //open SessionFactory
        SessionFactory sessionFactory = new Configuration().configure("com\\valerastudy\\example2\\hibernate.cfg.xml").buildSessionFactory();

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

        book.setBookName("Book 2");
        book.setBookCategory("Category 2");

        System.out.println("Book reseted before loading from DB: [id = " + book.getBookName() + ", name = " + book.getBookCategory() + "]");
        session = sessionFactory.openSession();
        session.beginTransaction();

        book = session.get(BookEx2.class, 1);

        session.getTransaction().commit();
        session.close();

        System.out.println("Book reseted before loading from DB: [id = " + book.getBookName() + ", name = " + book.getBookCategory() + "]");

        //close SessionFactory
        sessionFactory.close();
    }
}
