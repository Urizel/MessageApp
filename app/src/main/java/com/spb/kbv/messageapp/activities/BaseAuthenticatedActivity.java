package com.spb.kbv.messageapp.activities;

import android.content.Intent;
import android.os.Bundle;

public abstract class BaseAuthenticatedActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!application.getAuth().getUser().isLoggedIn()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
        onMessageAppCreate(savedInstanceState);
    }

    protected abstract void onMessageAppCreate(Bundle savedInstanceState);
}
