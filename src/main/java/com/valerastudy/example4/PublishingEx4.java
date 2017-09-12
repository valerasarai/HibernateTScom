package com.valerastudy.example4;

public class PublishingEx4 extends BookEx4 {
    private int publishingId;
    private String publishingName;

    public PublishingEx4(){}

    public PublishingEx4(int publishingId, String publishingName) {
        this.publishingId = publishingId;
        this.publishingName = publishingName;
    }

    public PublishingEx4(int bookId, String bookName, int publishingId, String publishingName) {
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
        return super.toString() + " PublishingEx4{" +
                "publishingId=" + publishingId +
                ", publishingName='" + publishingName + '\'' +
                '}';
    }
}
