package com.cksrb.rebook.DataForm;

/**
 * Created by cksrb on 2016. 12. 6..
 */

public class BookData {
    private String isbn;
    private String title;
    private String link;
    private String image_url;
    private String author;
    private int price;
    private String publisher;
    private String description;

    private String customerId;
    private String sellerId;
    private String sellPrice;

    private int type;

    public BookData(){
        customerId=null;
        sellerId=null;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getAuthor() {
        return author;
    }

    public int getPrice() {
        return price;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDescription() {
        return description;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public int getType() {
        return type;
    }
}
