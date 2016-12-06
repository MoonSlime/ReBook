package com.cksrb.rebook;

import android.app.Application;

import com.cksrb.rebook.DataForm.USER;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cksrb on 2016. 12. 5..
 */

public class ReBookApplication extends Application {
    public FirebaseDatabase firebaseDatabase ;//= FirebaseDatabase.getInstance();
    public DatabaseReference databaseReference ;//= firebaseDatabase.getReference();

    private String userId;
    private List<USER> userList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
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

    public List<USER> getUserList() {
        return userList;
    }
}


