package com.cksrb.rebook;

import android.graphics.drawable.Drawable;

/**
 * Created by Duck on 2016-12-07.
 */

public class ListViewItem{
    private Drawable bookCoverDrawable;
    private String sellerStr;
    private String bookNameStr;

    private String sellerId;
    private String isbn;
    private int type;

    private String userId;

    private String publisherStr;
    private int priceInt;
    private String sellerPriceStr;
    private String professorStr;

    public ListViewItem(Drawable bookcover, String bookName, String seller,String sellerId,String isbn,int type) {
        bookCoverDrawable = bookcover;
        bookNameStr = bookName;
        sellerStr = seller;
        this.sellerId=sellerId;
        this.isbn=isbn;
        this.type=type;
    }

    public ListViewItem(Drawable bookcover, String bookName, String seller,String sellerId,String isbn,int type, String publisher, int price, String sellerPrice) {
        bookCoverDrawable = bookcover;
        bookNameStr = bookName;
        sellerStr = seller;
        this.sellerId=sellerId;
        this.isbn=isbn;
        this.type=type;
        publisherStr = publisher;
        priceInt = price;
        sellerPriceStr = sellerPrice;
    }

    public ListViewItem(Drawable bookcover, String bookName, String seller,String sellerId,String isbn,int type, String publisher, int price, String sellerPrice, String professor) {
        bookCoverDrawable = bookcover;
        bookNameStr = bookName;
        sellerStr = seller;
        this.sellerId=sellerId;
        this.isbn=isbn;
        this.type=type;
        publisherStr = publisher;
        priceInt = price;
        sellerPriceStr = sellerPrice;
        professorStr = professor;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Drawable getBookCoverDrawable(){
        return this.bookCoverDrawable;
    }
    public String getBookName(){
        return this.bookNameStr;
    }
    public String getSeller(){
        return this.sellerStr;
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getType() {
        return type;
    }

    public String getUserId() {
        return userId;
    }

    public String getPublisher(){return publisherStr;}

    public int getPriceInt(){ return priceInt;}

    public String getSellerPriceStr(){ return sellerPriceStr;}

    public String getProfessorStr(){ return professorStr;}
}
