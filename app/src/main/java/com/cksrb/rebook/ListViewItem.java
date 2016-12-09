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

    public ListViewItem(Drawable bookcover, String bookName, String seller,String sellerId) {
        bookCoverDrawable = bookcover;
        bookNameStr = bookName;
        sellerStr = seller;
        this.sellerId=sellerId;
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
}
