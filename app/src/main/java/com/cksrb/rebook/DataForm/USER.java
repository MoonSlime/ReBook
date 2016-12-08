package com.cksrb.rebook.DataForm;

/**
 * Created by cksrb on 2016. 12. 6..
 */

public class USER {
    public String id;
    public String password;
    public String name;
    public String phone;

    public USER() {

    }
    public USER(String id, String password) {
        this.id = id;
        this.password = password;
        this.name=name;
        this.phone=phone;
    }

    public USER(String id, String password,String name,String phone) {
        this.id = id;
        this.password = password;
        this.name=name;
        this.phone=phone;
    }

    public boolean equals(USER user) {
        if (this.id.equals(user.id) && this.password.equals(user.password)) return true;

        return false;
    }
}