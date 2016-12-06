package com.cksrb.rebook;

import android.app.Application;

import com.cksrb.rebook.DataForm.Customer;

import java.util.List;

/**
 * Created by cksrb on 2016. 12. 5..
 */

public class ReBookApplication extends Application {
    private String userId;
    private List<Customer> customer;
    private List<String> seller;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    ReBookApplication(){
        userId="A";
    }

    public void setUserId(String userId){
        this.userId=userId;
    }

    public String getUserId(){
        return userId;
    }

    public List<Customer> getCustomer() {
        return customer;
    }

    public List<String> getSeller() {
        return seller;
    }
}


