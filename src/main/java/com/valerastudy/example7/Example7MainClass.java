package com.valerastudy.example7;

/*
* Exemplu 7
* Hibernate curat cu mySQL DB
* Salvarea obiectelor de care contin liste de obiecte List<Object>
*/

import com.valerastudy.example6.BookEx6;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.lang.reflect.Array;
import java.util.*;

public class Example7MainClass {


    public static String hibernateCfgXml = "com\\valerastudy\\example7\\hibernate.cfg.xml";

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure(hibernateCfgXml).buildSessionFactory();

// ------------------------------------------------------------------------------------------------------------
//        Create books for DB
        List<BookEx6> books = new ArrayList<BookEx6>();

        for (int i = 1; i <= 10; i++) {
            books.add(new BookEx6(i, "Book " + i));
        }

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (BookEx6 book : books) session.save(book);
        session.getTransaction().commit();
        session.close();

//        Print all books
        System.out.println();
        System.out.println("Print all books before commit:");
        for (BookEx6 book : books) System.out.println(book.toString());
        System.out.println();

// ------------------------------------------------------------------------------------------------------------
//        Extract all book from DB and create List of Books
        books = null;

        session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from BookEx6");
        books = query.list();
        session.getTransaction().commit();
        session.close();

//        Print all books
        System.out.println();
        System.out.println("Print all books after commit:");
        for (BookEx6 book : books) System.out.println(book.toString());
        System.out.println();

// ------------------------------------------------------------------------------------------------------------
//        Create Authors objects
        List<AuthorsEx7> authors = new ArrayList<AuthorsEx7>();
        for (int i = 1; i <= 5; i++) {
            List<BookEx6> temp = new ArrayList<BookEx6>();
            for (int j = 0; j < i; j++) {
            temp.add(books.get(j+i-1));
            }
          authors.add(new AuthorsEx7(i, "Author " + i, temp));
        }

//        Print all Authors
        System.out.println();
        System.out.println("Print all Authors before commit:");
        for (AuthorsEx7 author : authors) System.out.println(author.toString());
        System.out.println();

//        Save all Authors to DB
        session = sessionFactory.openSession();
        session.beginTransaction();

        for (AuthorsEx7 author : authors) session.save(author);

        session.getTransaction().commit();
        session.close();

        authors = null;

//        Extract all authors from DB and create List of Authors
        session = sessionFactory.openSession();
        session.beginTransaction();
        query = session.createQuery("from AuthorsEx7");

        authors = query.list();
        session.getTransaction().commit();
        session.close();

//        Print all Authors
        System.out.println();
        System.out.println("Print all Authors after commit:");
        for (AuthorsEx7 author : authors) System.out.println(author.toString());
        System.out.println();


        sessionFactory.close();
    }
}
