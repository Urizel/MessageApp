package com.spb.kbv.messageapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.spb.kbv.messageapp.R;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText userNameText;
    private EditText emailText;
    private EditText passwordText;
    private Button registerButton;
    private View progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userNameText = (EditText)findViewById(R.id.activity_register_userMame);
        emailText = (EditText)findViewById(R.id.activity_register_email);
        passwordText = (EditText)findViewById(R.id.activity_register_password);
        registerButton = (Button)findViewById(R.id.activity_register_button);
        progressBar = findViewById(R.id.activity_register_progressBar);

        registerButton.setOnClickListener(this);
        progressBar.setVisibility(View.GONE);


    }

    @Override
    public void onClick(View view) {
        if (view == registerButton) {
            application.getAuth().getUser().setIsLoggedIn(true);
            setResult(RESULT_OK);
            finish();
        }
    }
}
