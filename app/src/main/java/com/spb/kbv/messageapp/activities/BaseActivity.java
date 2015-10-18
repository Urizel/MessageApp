package com.spb.kbv.messageapp.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.spb.kbv.messageapp.infrastructure.MessageApplication;

public class BaseActivity extends ActionBarActivity {
    protected MessageApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        application = (MessageApplication)getApplication();
    }
}
