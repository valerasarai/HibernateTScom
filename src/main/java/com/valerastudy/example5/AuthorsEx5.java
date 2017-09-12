package com.valerastudy.example5;

public class AuthorsEx5 extends BookEx5 {
    private int authorsId;
    private String authorsName;

    public AuthorsEx5(){}

    public AuthorsEx5(int bookId, String bookName, int authorsId, String authorsName) {
        super(bookId, bookName);
        this.authorsId = authorsId;
        this.authorsName = authorsName;
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

    @Override
    public String toString() {
        return super.toString() + " AuthorsEx5{" +
                "authorsId=" + authorsId +
                ", authorsName='" + authorsName + '\'' +
                '}';
    }
}
