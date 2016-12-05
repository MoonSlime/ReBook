package com.cksrb.rebook;

import android.app.Application;

/**
 * Created by cksrb on 2016. 12. 5..
 */

public class ReBookApplication extends Application {
    private String userId;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    ReBookApplication(){
        userId="No_Id";
    }

    public void setUserId(String userId){
        this.userId=userId;
    }

    public String getUserId(){
        return userId;
    }

}
