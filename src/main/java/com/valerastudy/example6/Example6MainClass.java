package com.valerastudy.example6;

/*
* Exemplu 6
* Hibernate curat cu mySQL DB
* Salvarea obiectelor de care contin obiecte
* VARIANTA un singur obiect ca instanta la una din clase
*/

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Example6MainClass {


    public static String hibernateCfgXml = "com\\valerastudy\\example6\\hibernate.cfg.xml";

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure(hibernateCfgXml).buildSessionFactory();

// ------------------------------------------------------------------------------------------------------------
//        Create books for DB
        List<BookEx6> books = new ArrayList<BookEx6>();

        for (int i = 1; i <= 5; i++) {
            books.add(i - 1, new BookEx6(i, "Book " + i));
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
        List<AuthorsEx6> authors = new ArrayList<AuthorsEx6>();
        for (int i = 1; i <= 5; i++) {
            authors.add(new AuthorsEx6(i, "Author " + i, books.get(i - 1)));
        }

//        Print all Authors
        System.out.println();
        System.out.println("Print all Authors before commit:");
        for (AuthorsEx6 author : authors) System.out.println(author.toString());
        System.out.println();

//        Save all Authors to DB
        session = sessionFactory.openSession();
        session.beginTransaction();

        for (AuthorsEx6 author : authors) session.save(author);

        session.getTransaction().commit();
        session.close();

        authors = null;

//        Extract all authors from DB and create List of Authors
        session = sessionFactory.openSession();
        session.beginTransaction();
        query = session.createQuery("from AuthorsEx6");

        authors = query.list();
        session.getTransaction().commit();
        session.close();

//        Print all Authors
        System.out.println();
        System.out.println("Print all Authors after commit:");
        for (AuthorsEx6 author : authors) System.out.println(author.toString());
        System.out.println();

// ------------------------------------------------------------------------------------------------------------
//        Manipulari mai complicate cu obiectele
//        sau poate mie mi se pare asa

        // crearea de autori noi
        for (int i = 1; i <= 5; i++) {
            authors.add(new AuthorsEx6(authors.size() + 1, "Author " + (authors.size() + 1), books.get(i - 1)));
        }
//
        books.add(new BookEx6((books.size() + 1), "Book " + (books.size() + 1)));
        authors.add(new AuthorsEx6((authors.size() + 1), "Author " + (authors.size() + 1), books.get((books.size() - 1))));

//        Print all data before commit
        System.out.println();
        System.out.println("Print all book before commit 2:");
        for (BookEx6 book : books) System.out.println(book.toString());
        System.out.println();
        System.out.println();
        System.out.println("Print all Authors before commit 2:");
        for (AuthorsEx6 author : authors) System.out.println(author.toString());
        System.out.println();


//        Save/Update all to/in DB
        session = sessionFactory.openSession();
        session.beginTransaction();

        for (BookEx6 book : books) session.update(book);
        for (AuthorsEx6 author : authors) session.update(author);

        session.getTransaction().commit();
        session.close();

        books = null;
        authors = null;

//        Extract all data from DB
        session = sessionFactory.openSession();
        session.beginTransaction();

        query = session.createQuery("from BookEx6");
        books = query.list();

        query = session.createQuery("from AuthorsEx6");
        authors = query.list();

        session.getTransaction().commit();
        session.close();

//        Print all data
        for (BookEx6 book : books) System.out.println(book.toString());
        for (AuthorsEx6 author : authors) System.out.println(author.toString());

        sessionFactory.close();
    }
}
