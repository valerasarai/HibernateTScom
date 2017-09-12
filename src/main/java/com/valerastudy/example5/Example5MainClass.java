package com.valerastudy.example5;

/*
* Exemplu 4
* Hibernate curat cu mySQL DB
* Salvarea obiectelor din clasele mostenitoare
* VARIANTA CU UN SINGUR TABEL IN DB
*/

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Example5MainClass {


    public static String hibernateCfgXml = "com\\valerastudy\\example5\\hibernate.cfg.xml";

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure(hibernateCfgXml).buildSessionFactory();
        Session session = sessionFactory.openSession();

// ------------------------------------------------------------------------------------------------------------
//        Extract all book from DB and create List of Books
        session.beginTransaction();
        Query query = session.createQuery("from BookEx5");
        List<BookEx5> books = query.list();
        session.getTransaction().commit();
        session.close();

//        Print all books
        for (BookEx5 book : books) System.out.println(book.toString());

// ------------------------------------------------------------------------------------------------------------
//        Create Authors objects
        List<AuthorsEx5> authors = new ArrayList<AuthorsEx5>();
        for (int i = 1; i <= 5; i++) {
            authors.add(new AuthorsEx5(books.get(i - 1).getBookId(), books.get(i - 1).getBookName(), i, "Author " + i));
        }

//        Print all Authors
        for (AuthorsEx5 author : authors) System.out.println(author.toString());

//        Save all Authors to DB
        session = sessionFactory.openSession();
        session.beginTransaction();

        for (AuthorsEx5 author : authors) session.save(author);

        session.getTransaction().commit();
        session.close();

        authors = null;

//        Extract all authors from DB and create List of Authors
        session = sessionFactory.openSession();
        session.beginTransaction();
        query = session.createQuery("from AuthorsEx5");

        authors = query.list();
        session.getTransaction().commit();
        session.close();

//        Print all Authors
        for (AuthorsEx5 author : authors) System.out.println(author.toString());

// ------------------------------------------------------------------------------------------------------------
//        Create Publishing objects
        List<PublishingEx5> publishings = new ArrayList<PublishingEx5>();
        for (int i = 1; i <= 5; i++) {
            publishings.add(new PublishingEx5(books.get(i - 1).getBookId(), books.get(i - 1).getBookName(), i, "Publishing " + i));
        }

//        Print all Publishing
        for (PublishingEx5 publishing: publishings) System.out.println(publishing.toString());

//        Save all Publishing to DB
        session = sessionFactory.openSession();
        session.beginTransaction();

        for (PublishingEx5 publishing : publishings) session.save(publishing);

        session.getTransaction().commit();
        session.close();

        publishings = null;

//        Extract all Publishing from DB and create List of Publishing
        session = sessionFactory.openSession();
        session.beginTransaction();
        query = session.createQuery("from PublishingEx5");

        publishings = query.list();
        session.getTransaction().commit();
        session.close();

//        Print all Publishing
        for (PublishingEx5 publishing : publishings) System.out.println(publishing.toString());

// ------------------------------------------------------------------------------------------------------------
//        Extract only BookEx5 object what no have childres

        books = null;

        session = sessionFactory.openSession();
        session.beginTransaction();

//        Query query = session.createQuery("SELECT p from BookEx5 p where p.class = BookEx5");
        query = session.createQuery("SELECT p from BookEx5 p where TYPE(p) = BookEx5");
        books = query.list();

        session.getTransaction().commit();
        session.close();

//        Print all books only
        for (BookEx5 book : books) System.out.println(book.toString());

        sessionFactory.close();
    }
}
