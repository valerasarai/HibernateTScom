package com.valerastudy.example5;

public class PublishingEx5 extends BookEx5 {
    private int publishingId;
    private String publishingName;

    public PublishingEx5(){}

    public PublishingEx5(int publishingId, String publishingName) {
        this.publishingId = publishingId;
        this.publishingName = publishingName;
    }

    public PublishingEx5(int bookId, String bookName, int publishingId, String publishingName) {
        super(bookId, bookName);
        this.publishingId = publishingId;
        this.publishingName = publishingName;
    }

    public int getPublishingId() {
        return publishingId;
    }

    public void setPublishingId(int publishingId) {
        this.publishingId = publishingId;
    }

    public String getPublishingName() {
        return publishingName;
    }

    public void setPublishingName(String publishingName) {
        this.publishingName = publishingName;
    }

    @Override
    public String toString() {
        return super.toString() + " PublishingEx5{" +
                "publishingId=" + publishingId +
                ", publishingName='" + publishingName + '\'' +
                '}';
    }
}
