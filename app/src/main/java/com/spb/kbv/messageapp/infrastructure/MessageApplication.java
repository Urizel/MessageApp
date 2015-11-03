package com.spb.kbv.messageapp.infrastructure;

import android.app.Application;

import com.spb.kbv.messageapp.services.Module;
import com.squareup.otto.Bus;

public class MessageApplication extends Application{
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
