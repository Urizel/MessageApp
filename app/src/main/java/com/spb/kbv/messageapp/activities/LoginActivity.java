package com.spb.kbv.messageapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.spb.kbv.messageapp.R;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private View loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = findViewById(R.id.activity_login_login);
        if (loginButton != null) {
            loginButton.setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View view) {
        if (view == loginButton)
            startActivity(new Intent(this, LoginNarrowActivity.class));
    }
}
