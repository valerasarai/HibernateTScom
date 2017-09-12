package com.valerastudy.example4;

/*
* Exemplu 4
* Hibernate curat cu mySQL DB
* Salvarea obiectelor din clasele mostenitoare
*/

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Example4MainClass {


    public static String hibernateCfgXml = "com\\valerastudy\\example4\\hibernate.cfg.xml";

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure(hibernateCfgXml).buildSessionFactory();
        Session session = sessionFactory.openSession();

//        Extract all book from DB and create List of Books
        session.beginTransaction();
        Query query = session.createQuery("from BookEx4");
        List<BookEx4> books = query.list();
        session.getTransaction().commit();
        session.close();

//        Print all books
        for (BookEx4 book : books) System.out.println(book.toString());

//        Create Authors objects
        List<AuthorsEx4> authors = new ArrayList<AuthorsEx4>();
        for (int i = 1; i <= 5; i++) {
            authors.add(new AuthorsEx4(books.get(i - 1).getBookId(), books.get(i - 1).getBookName(), i, "Author " + i));
        }

//        Print all Authors
        for (AuthorsEx4 author : authors) System.out.println(author.toString());

        session = sessionFactory.openSession();
        session.beginTransaction();
        session.getTransaction().commit();

        for (AuthorsEx4 author : authors) session.save(author);

        session.close();
        sessionFactory.close();
    }
}
