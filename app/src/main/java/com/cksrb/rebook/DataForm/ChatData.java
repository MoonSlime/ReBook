package com.cksrb.rebook.DataForm;

/**
 * Created by cksrb on 2016. 11. 21..
 */

public class ChatData {
    private String userId;
    private String message;

    public ChatData() { }

    public ChatData(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}