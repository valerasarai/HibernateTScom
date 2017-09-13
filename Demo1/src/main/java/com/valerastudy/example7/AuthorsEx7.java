package com.valerastudy.example7;

import com.valerastudy.example6.BookEx6;

import java.util.ArrayList;
import java.util.List;

public class AuthorsEx7 {
    private int authorsId;
    private String authorsName;
    private List<BookEx6> books = new ArrayList<BookEx6>();

    public AuthorsEx7(){}

    public AuthorsEx7(int authorsId, String authorsName, List<BookEx6> books) {
        this.authorsId = authorsId;
        this.authorsName = authorsName;
        this.books = books;
    }

    public int getAuthorsId() {
        return authorsId;
    }

    public void setAuthorsId(int authorsId) {
        this.authorsId = authorsId;
    }

    public String getAuthorsName() {
        return authorsName;
    }

    public void setAuthorsName(String authorsName) {
        this.authorsName = authorsName;
    }

    public List<BookEx6> getBooks() {
        return books;
    }

    public void setBooks(List<BookEx6> books) {
        this.books = books;
    }

    /*
    public void setBooks(BookEx6 book) {
        this.books.add(book);
    }
*/

    @Override
    public String toString() {
        String result = "";
        result = "AuthorsEx7{" + "authorsId=" + authorsId + ", authorsName='" + authorsName + "', Books nr = " + books.size() + "}";

        for (BookEx6 book : books)
            result += "\n\t\t" + book.toString();

        return result;
    }
}
