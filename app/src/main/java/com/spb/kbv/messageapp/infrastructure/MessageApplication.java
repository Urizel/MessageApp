package com.spb.kbv.messageapp.infrastructure;

import android.app.Application;

public class MessageApplication extends Application{

    private Auth auth;

    @Override
    public void onCreate() {
        super.onCreate();
        auth = new Auth(this);
    }

    public Auth getAuth() {
        return auth;
    }
}
