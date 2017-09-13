package com.valerastudy.example8;

/*
* Exemplu 8
* Hibernate curat cu mySQL DB
* Criteria Queries, Restrictions and Projections
*/

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.*;
import org.hibernate.query.Query;

import java.util.*;

public class Example8MainClass {

    public static String hibernateCfgXml = "com\\valerastudy\\example8\\hibernate.cfg.xml";

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure(hibernateCfgXml).buildSessionFactory();
        Session session = sessionFactory.openSession();

// ------------------------------------------------------------------------------------------------------------
// initializare baza de date
// ------------------------------------------------------------------------------------------------------------
//        Create books
        List<BookEx8> books = new ArrayList<BookEx8>();

        for (int i = 1; i <= 10; i++) {
            books.add(new BookEx8(i, "Book " + i, "Author " + i, new Random().nextInt(500)));
        }

//        Print all books
        printList("Print all books before commit:", books);

// ------------------------------------------------------------------------------------------------------------
//        Insert all books

// - NOTA 1 ---------------------------------------------------------------------------------------------------
//        Functia session.save poate fi accesata inainde de a deshide o tranzactie
//        dar aceasta nu se va realiza pina nu va fi indicat locul tranzactiei in sesiune

        for (BookEx8 book : books) session.save(book);

// ------------------------------------------------------------------------------------------------------------

// - NOTA 2 ---------------------------------------------------------------------------------------------------
//        Initierea si realizarea tranzactiei.
//        este posibila doa in cadrul sesiunii deschise
//        folosita pentru a realiza manipularile de gen session.save

        session.beginTransaction();
        session.getTransaction().commit();

// ------------------------------------------------------------------------------------------------------------

// - NOTA 3 ---------------------------------------------------------------------------------------------------
//        Daca incid sesiunea aici atunci obiectele introduse in BD ramin deatasate si orice manipulare cu ele in aplicatie
//        nu se va realiza si in baza respectiv dupa deschiderea altei sesiuni si scoaterea obiectelor de acolo acestea nu
//        vor avea schimbarile ulterioare.
//        CONFITIE DE REALIZARE: scoate comentariul la cod in Nota 4

//        session.close();

// ------------------------------------------------------------------------------------------------------------


// ------------------------------------------------------------------------------------------------------------
//        modificarea unui element
        System.out.println("books[0] = before change: " + books.get(0).toString());
        books.get(0).setBookAuthorName("A0");
        books.get(0).setBookPagesNumber(80);
        System.out.println("books[0] = after change: " + books.get(0).toString());
        books.get(1).setBookPagesNumber(200);

// ------------------------------------------------------------------------------------------------------------
//        Extract all book from DB and create List of Books
        books = null;

// - NOTA 4 ---------------------------------------------------------------------------------------------------
//        deschiderea sesiunii noi pentru varianta de la Nota 4
//        CONFITIE DE REALIZARE: scoate comentariul la cod in Nota 3

//        session = sessionFactory.openSession();

// ------------------------------------------------------------------------------------------------------------

        Query query = session.createQuery("from BookEx8");
        books = query.list();

//        Print all books
        printList("Print all books after commit:", books);

// ------------------------------------------------------------------------------------------------------------
// Exemplu 1 Creating a Criteria instance
// ------------------------------------------------------------------------------------------------------------
        System.out.println("\n" + "Exemplu 1 Creating a Criteria instance:");
        books = null;

        Criteria criteria = session.createCriteria(BookEx8.class);
        criteria.setMaxResults(5);
        books = criteria.list();

        printList("Print all Criteria books:", books);

// ------------------------------------------------------------------------------------------------------------
//        modificarea unui element
        books.get(0).setBookAuthorName("A_EX_1");

        books = null;

        query = session.createQuery("from BookEx8");
        books = query.list();

        printList("Print all books after modify in Criteria selection:", books);

// ------------------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------
// Exemplu 2 Narrowing the result set
// ------------------------------------------------------------------------------------------------------------
        System.out.println("\n" + "Exemplu 2 Narrowing the result set:");
        books = null;
        books = session.createCriteria(BookEx8.class)
                .add(Restrictions.like("bookName", "Book 1%"))
                .add(Restrictions.between("bookPagesNumber", 100, 300))
                .list();
        printList("Exemplu 2. Rezultatul 1:", books);

        books = null;
        books = session.createCriteria(BookEx8.class)
                .add(Restrictions.like("bookName", "Book%"))
                .add(Restrictions.or(
                        Restrictions.eq("bookId", 5),
                        Restrictions.isNull("bookName")))
                .list();
        printList("Exemplu 2. Rezultatul 2:", books);

        books = null;
        books = session.createCriteria(BookEx8.class)
                .add(Restrictions.disjunction()
                        .add(Restrictions.isNull("bookId"))
                        .add(Restrictions.eq("bookId", 1))
                        .add(Restrictions.eq("bookId", 2))
                        .add(Restrictions.eq("bookId", 7)))
                .list();
        printList("Exemplu 2. Rezultatul 4:", books);

/*
//      Nu merge ciota
        books = null;
        books = session.createCriteria(BookEx8.class)
                .add(Restrictions.sqlRestriction("lower({alias}.name) like lower(1)", "Book 1%",Hibernate.STRING))
                .list();
        printList("Exemplu 2. Rezultatul 3:", books);
*/

        books = null;
        Property property = Property.forName("bookId");

        books = session.createCriteria(BookEx8.class)
                .add(Restrictions.disjunction()
                        .add(property.isNull())
                        .add(property.eq(1))
                        .add(property.eq(2))
                        .add(property.eq(7)))
                .list();
        printList("Exemplu 2. Rezultatul 5:", books);

// ------------------------------------------------------------------------------------------------------------

// ------------------------------------------------------------------------------------------------------------
// Exemplu 3 Ordering the results
// ------------------------------------------------------------------------------------------------------------
        System.out.println("\n" + "Exemplu 3 Ordering the results:");

        books = null;
        books = session.createCriteria(BookEx8.class)
                .add(Restrictions.like("bookName", "B%"))
                .addOrder(Order.desc("bookId"))
//                .addOrder(Order.asc("bookId"))
                .list();
        printList("Exemplu 3. Rezultatul 1:", books);

        books = null;
        books = session.createCriteria(BookEx8.class)
                .add(Property.forName("bookName").like("B%"))
                .addOrder(Property.forName("bookId").desc())
//                .addOrder(Property.forName("bookId").asc())
                .list();
        printList("Exemplu 3. Rezultatul 2:", books);

// ------------------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------
// Exemplu 4 Associations
// Din  pacate nu am inteles ce e asta si, probabil, nici programul in forma actuala nu permite sa fac asta
// ------------------------------------------------------------------------------------------------------------
        System.out.println("\n" + "Exemplu 4 Associations");
        System.out.println("il voi implementa pe urma!!!!! :D");
/*
        books = null;
        books = session.createCriteria(BookEx8.class)
                .add(Restrictions.like("bookName", "B%"))
//                .createCriteria("bookAuthor")
//                    .add(Restrictions.like("bookAuthor", "A%"))
                .list();
        printList("Exemplu 4. Rezultatul 1:", books);
*/
// ------------------------------------------------------------------------------------------------------------

// ------------------------------------------------------------------------------------------------------------
// Exemplu 5 Dynamic association fetching
// ------------------------------------------------------------------------------------------------------------
        System.out.println("\n" + "Exemplu 5 Dynamic association fetching:");
        System.out.println("il voi implementa pe urma!!!!! :D");
// ------------------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------
// Exemplu 6 Example queries
// ------------------------------------------------------------------------------------------------------------
        System.out.println("\n" + "Exemplu 6 Example queries:");

        books = null;

        BookEx8 book = new BookEx8();
        book.setBookId(2);
        book.setBookName("Book 2");
        book.setBookAuthorName("Author 2");
        book.setBookPagesNumber(200);

        books = session.createCriteria(BookEx8.class)
                .add(Example.create(book)
                        .excludeProperty("bookPagesNumber"))
                .list();

        printList("Exemplu 6. Rezultatul 1:", books);
        // nu inteleg de ce nu imi returneaza books[2]
        // este o problema cu nebunul ista de "bookPagesNumber" - daca scot restrictia atunci nu merge

        books = null;
        Example example = Example.create(book)
                .excludeZeroes()
                .excludeProperty("bookPagesNumber")
                .ignoreCase()
                .enableLike();

        books = session.createCriteria(BookEx8.class)
                .add(example)
                .list();
        printList("Exemplu 6. Rezultatul 2:", books);

        books = null;
        books = session.createCriteria(BookEx8.class)
                .add(Example.create(book))
//                .createCriteria("bookName")
//                    .add(Example.create(book.getBookName()))
                .list();
        printList("Exemplu 6. Rezultatul 3:", books);
        // nu inteleg indeobste ce face chestia asta

// ------------------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------------------
// Exemplu 7 Projections, aggregation and grouping
// ------------------------------------------------------------------------------------------------------------
        System.out.println("\n" + "Exemplu 7 Projections, aggregation and grouping:");

        books = null;
        books = session.createCriteria(BookEx8.class)
                .setProjection(Projections.rowCount())
                .add(Restrictions.like("bookName","Book 1%"))
                .list();
        printList("Exemplu 7. Rezultatul 1:", books);

        books = null;
        books = session.createCriteria(BookEx8.class)
                .setProjection(Projections.projectionList()
                .add(Projections.rowCount())
                .add(Projections.avg("bookPagesNumber"))
                .add(Projections.max("bookPagesNumber"))
                .add(Projections.groupProperty("bookId")))
                .list();
//        System.out.println(books.getClass().getName());
        printList("Exemplu 7. Rezultatul 2:", books);
        //hz nu stiu ce e cu figneaua asta si dece imi returneaza o harababura

// ------------------------------------------------------------------------------------------------------------

/*
// ------------------------------------------------------------------------------------------------------------
// Exemplu 1 Creating a Criteria instance
// ------------------------------------------------------------------------------------------------------------
        System.out.println("\n" + "Exemplu 1 Creating a Criteria instance:");
        books = null;
// ------------------------------------------------------------------------------------------------------------
*/


// ------------------------------------------------------------------------------------------------------------
//        Colose session
        session.close();
//        Colose sessionFactory
        sessionFactory.close();
    }

    public static void printList(String message, List list) {
        System.out.println("\n" + message);
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }

    public static void printList(String message, ArrayList list) {
        System.out.println("\n" + message);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }
}
