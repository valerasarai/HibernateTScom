package com.valerastudy.example6;

public class AuthorsEx6 {
    private int authorsId;
    private String authorsName;
    private BookEx6 book;

    public AuthorsEx6(){}

    public AuthorsEx6(int authorsId, String authorsName, BookEx6 book) {
        this.authorsId = authorsId;
        this.authorsName = authorsName;
        this.book = book;
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

    public BookEx6 getBook() {
        return book;
    }

    public void setBook(BookEx6 book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "AuthorsEx6{" +
                "authorsId=" + authorsId +
                ", authorsName='" + authorsName + '\'' +
                ", " + book.toString() +
                '}';
    }
}
