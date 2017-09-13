package com.valerastudy.example4;

public class AuthorsEx4 extends BookEx4 {
    private int authorsId;
    private String authorsName;

    public AuthorsEx4(){}

    public AuthorsEx4(int bookId, String bookName, int authorsId, String authorsName) {
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
        return super.toString() + " AuthorsEx4{" +
                "authorsId=" + authorsId +
                ", authorsName='" + authorsName + '\'' +
                '}';
    }
}
