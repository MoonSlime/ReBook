package com.cksrb.rebook;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Duck on 2016-12-07.
 */

public class ListViewItem{
    private Drawable bookCoverDrawable;

    private String bookCoverUrl;
    private String sellerStr;
    private String bookNameStr;

    private String sellerId;
    private String isbn;
    private int type;

    private String userId;

    private String publisherStr;
    private int priceInt;
    private String sellerPriceStr;
    private String classStr;
    private String etcStr;

    public ListViewItem(String bookCover, String bookName, String seller,String sellerId,String isbn,int type) {
        bookCoverUrl = bookCover;
        bookNameStr = bookName;
        sellerStr = seller;
        this.sellerId=sellerId;
        this.isbn=isbn;
        this.type=type;
    }

    public ListViewItem(String bookcover, String bookName, String seller,String sellerId,String isbn,int type, String publisher, int price, String sellerPrice, String etc) {
        bookCoverUrl = bookcover;
        bookNameStr = bookName;
        sellerStr = seller;
        this.sellerId=sellerId;
        this.isbn=isbn;
        this.type=type;
        publisherStr = publisher;
        priceInt = price;
        sellerPriceStr = sellerPrice;
        etcStr = etc;
    }

    public ListViewItem(String bookcover, String bookName, String seller,String sellerId,String isbn,int type, String publisher, int price, String sellerPrice, String className, String etc) {
        bookCoverUrl = bookcover;
        bookNameStr = bookName;
        sellerStr = seller;
        this.sellerId=sellerId;
        this.isbn=isbn;
        this.type=type;
        publisherStr = publisher;
        priceInt = price;
        sellerPriceStr = sellerPrice;
        classStr = className;
        etcStr = etc;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookCoverUrl() { return bookCoverUrl; }

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

    public String getClassStr(){ return classStr; }

    public String getEtcStr() { return etcStr; }

}
