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

    private String class_name;
    private String customerId;
    private String sellerId;
    private String sellPrice;
    private String etc;
    private String professor;

    private int type;

    public BookData(){
        customerId=null;
        sellerId=null;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
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

    public void setProfessor(String professor){
        this.professor = professor;
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

    public String getEtc() {
        return etc;
    }

    public String getProfessor(){
        return  professor;
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

    public String getClass_name() {
        return class_name;
    }


    public boolean search(String search){
        search=search.toLowerCase();

        if(title!=null&&title.toLowerCase().contains(search))return true;
        if(author!=null&&author.toLowerCase().contains(search))return true;
        if(publisher!=null&&publisher.toLowerCase().contains(search))return true;
        if(class_name!=null&&class_name.toLowerCase().contains(search))return true;

        return false;
    }
}
