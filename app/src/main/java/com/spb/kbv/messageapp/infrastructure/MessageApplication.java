package com.spb.kbv.messageapp.infrastructure;

import android.app.Application;
import android.net.Uri;

import com.spb.kbv.messageapp.services.Module;
import com.squareup.otto.Bus;

public class MessageApplication extends Application{
    public static final Uri API_ENDPOINT = Uri.parse("http://yora-playground.3dbuzz.com");
    public static final String TOKEN = "f2b36dd2a4d84bff90e161de6323efbe";
    private Auth auth;
    private Bus bus;

    public MessageApplication(){
        bus = new Bus();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        auth = new Auth(this);
        Module.register(this);
    }

    public Auth getAuth() {
        return auth;
    }

    public Bus getBus(){
        return bus;
    }
}
