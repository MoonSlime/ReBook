package com.cksrb.rebook.DataForm;

/**
 * Created by cksrb on 2016. 11. 21..
 */

public class ChatData {
    private String cmpstr;
    private String from;
    private String to;
    private String message;

    public ChatData() { }

    public ChatData(String from,String to, String message) {
        this.from =from;
        this.to=to;
        this.message = message;

        if(from.compareTo(to)>0)cmpstr=to+"|"+from;
        else cmpstr=from+"|"+to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }

    public String getCmpstr() {
        return cmpstr;
    }
}