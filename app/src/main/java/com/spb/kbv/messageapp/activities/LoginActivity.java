package com.spb.kbv.messageapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.spb.kbv.messageapp.R;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLogin(View view) {
        application.getAuth().getUser().setIsLoggedIn(true);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
