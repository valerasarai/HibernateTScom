package com.valerastudy.example8;

public class BookEx8 {
    private int bookId;
    private String bookName;
    private String bookAuthorName;
    private int bookPagesNumber;

    public BookEx8() {
    }

    public BookEx8(int bookId, String bookName, String bookAuthorName, int bookPagesNumber) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthorName = bookAuthorName;
        this.bookPagesNumber = bookPagesNumber;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthorName() {
        return bookAuthorName;
    }

    public void setBookAuthorName(String bookAuthor) {
        this.bookAuthorName = bookAuthor;
    }

    public int getBookPagesNumber() {
        return bookPagesNumber;
    }

    public void setBookPagesNumber(int bookPagesNumber) {
        this.bookPagesNumber = bookPagesNumber;
    }

    @Override
    public String toString() {
        return "BookEx8{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthorName + '\'' +
                ", bookPagesNumber='" + bookPagesNumber + '\'' +
                '}';
    }
}
