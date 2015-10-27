package com.spb.kbv.messageapp.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.spb.kbv.messageapp.R;
import com.spb.kbv.messageapp.infrastructure.MessageApplication;
import com.spb.kbv.messageapp.views.NavDrawer;

public class BaseActivity extends ActionBarActivity {
    protected MessageApplication application;
    protected Toolbar toolbar;
    protected NavDrawer navDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        application = (MessageApplication)getApplication();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        toolbar = (Toolbar)findViewById(R.id.include_toolbar);
        if (toolbar != null){
            setSupportActionBar(toolbar);
        }
    }
    protected void setNavDrawer(NavDrawer navDrawer){
        this.navDrawer = navDrawer;
        this.navDrawer.create();
    }

    public Toolbar getToolbar(){
        return toolbar;
    }
}
