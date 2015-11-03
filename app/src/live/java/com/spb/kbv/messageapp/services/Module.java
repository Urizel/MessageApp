package com.spb.kbv.messageapp.services;

import android.util.Log;

import com.spb.kbv.messageapp.infrastructure.MessageApplication;

public  class Module {
    public static void register (MessageApplication application) {
        Log.e("Module", "LIVE REGISTER METHOD CALLED");
    }
}