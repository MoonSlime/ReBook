package com.cksrb.rebook.DataForm;

/**
 * Created by cksrb on 2016. 12. 6..
 */

public class Seller {
    private String userId;
    private BookData bookData;

    public Seller(){
        bookData = new BookData();
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBookData(BookData bookData) {
        /*this.bookData.setIsbn(bookData.getIsbn());
        this.bookData.setTitle(bookData.getTitle());
        this.bookData.setLink(bookData.getLink());
        this.bookData.setImage_url(bookData.getImage_url());
        this.bookData.setAuthor(bookData.getAuthor());
        this.bookData.setPrice(bookData.getPrice());
        this.bookData.setPublisher(bookData.getPublisher());
        this.bookData.setDescription(bookData.getDescription());*/
        this.bookData = bookData;
    }

    public String getUserId() {
        return userId;
    }

    public BookData getBookData() {
        return bookData;
    }
}
