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

        // crearea unui obiect Book nou
        books.add(new BookEx6((books.size() + 1), "Book " + (books.size() + 1)));

        // crearea unui obiect Authors cu acel Book nou
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

        for (BookEx6 book : books) session.saveOrUpdate(book);
        for (AuthorsEx6 author : authors) session.saveOrUpdate(author);

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
        System.out.println();
        for (BookEx6 book : books) System.out.println(book.toString());
        System.out.println();
        System.out.println();
        for (AuthorsEx6 author : authors) System.out.println(author.toString());
        System.out.println();

// ------------------------------------------------------------------------------------------------------------
//        Manipulari interesante de tot
//        sau poate iara eu inventez

        // realizarea modificarilor cu listele

        authors.add(new AuthorsEx6(authors.size() + 1, "Author " + (authors.size() + 1),
                new BookEx6((books.size()+1),"Book "+(books.size()+1))));
        authors.get(6).setAuthorsName("Other Author 7");
        authors.get(6).setBook(new BookEx6((books.size()+2),"Book "+(books.size()+2)));

/*
        //Varianta 1 - mai simpla cum rezolvam problema cu books
        //Se introduc obiectele injectate in authors in book prin getBook()
        //apoi facem saveOrUpdate
        books.add(authors.get((authors.size() - 1)).getBook());
        books.add(authors.get(6).getBook());
        session = sessionFactory.openSession();
        session.beginTransaction();
            for (BookEx6 book : books) session.saveOrUpdate(book);
            for (AuthorsEx6 author : authors) session.saveOrUpdate(author);
        session.getTransaction().commit();
        session.close();
*/

        //Varianta 2 - mai interesanta cum rezolvam problema cu books
        //Se introduc obiectele injectate direct in tabel prin saveOrUpdate
        session = sessionFactory.openSession();
        session.beginTransaction();
            session.saveOrUpdate(authors.get((authors.size() - 1)).getBook());
            session.saveOrUpdate(authors.get(6).getBook());
            for (AuthorsEx6 author : authors) session.saveOrUpdate(author);
        session.getTransaction().commit();
        session.close();

        //Varianta xxx - mai sint variante de genul sa caute automat obiectele de tip book din cele injectate ulterior
        //si sa faca Save pentru cele care nu au fost gasite in tebel
        //si altele tot atit de bune dar deamu e tirziu si is obosit.

        sessionFactory.close();
    }
}
