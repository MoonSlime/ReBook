package com.cksrb.rebook.DataForm;

/**
 * Created by cksrb on 2016. 12. 9..
 */

public class WishData {
    private String isbn;
    private String sellerId;
    private String userId;
    private int type;
    private String bookCover;

    public WishData(){

    }

    public WishData(String userId,String isbn,String sellerId,int type,String bookCover){
        this.userId=userId;
        this.isbn=isbn;
        this.type=type;
        this.sellerId=sellerId;
        this.bookCover=bookCover;
    }

    public String getUserId() {
        return userId;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getType() {
        return type;
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getBookCover(){ return bookCover; }
}
