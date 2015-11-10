package com.spb.kbv.messageapp.activities;

import android.os.Bundle;
import com.spb.kbv.messageapp.R;
import com.spb.kbv.messageapp.views.MainNavDrawer;


public class MainActivity extends BaseAuthenticatedActivity {

    @Override
    protected void onMessageAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Inbox");

        setNavDrawer(new MainNavDrawer(this));


    }
}
