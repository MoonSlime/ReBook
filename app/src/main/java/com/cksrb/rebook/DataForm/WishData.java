package com.cksrb.rebook.DataForm;

/**
 * Created by cksrb on 2016. 12. 9..
 */

public class WishData {
    private String isbn;
    private String sellerId;
    private String userId;
    private int type;

    public WishData(){

    }

    public WishData(String userId,String isbn,String sellerId,int type){
        this.userId=userId;
        this.isbn=isbn;
        this.type=type;
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
}