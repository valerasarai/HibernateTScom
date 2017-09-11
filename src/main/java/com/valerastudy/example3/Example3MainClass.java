package com.valerastudy.example3;
/*
    Aici este aceeasi fignea ca in exemplu 2 numai ca pe mySQL, pentru a vedea concret ce se intimpla
    este ambiguu acest H2
*/

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Example3MainClass {


    public static String hibernateCfgXml = "com\\valerastudy\\example3\\hibernate.cfg.xml";

    public static void main(String[] args) {
        BookEx3 book = new BookEx3();
        book.setBookName("Book 1");
        book.setBookCategory("Category 1");

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

        book.setBookName("Book 2");
        book.setBookCategory("Category 2");

        System.out.println("Book reseted before loading from DB: [id = " + book.getBookName() + ", name = " + book.getBookCategory() + "]");
        session = sessionFactory.openSession();
        session.beginTransaction();

        book = session.get(BookEx3.class, 1);

        session.getTransaction().commit();
        session.close();

        System.out.println("Book reseted before loading from DB: [id = " + book.getBookName() + ", name = " + book.getBookCategory() + "]");

        //close SessionFactory
        sessionFactory.close();
    }
}
