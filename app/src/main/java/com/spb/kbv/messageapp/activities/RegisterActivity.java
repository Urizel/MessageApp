package com.spb.kbv.messageapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.spb.kbv.messageapp.R;
import com.spb.kbv.messageapp.services.Account;
import com.squareup.otto.Subscribe;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText usernameText;
    private EditText emailText;
    private EditText passwordText;
    private Button registerButton;
    private View progressBar;
    private String defaultRegisterButtonText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameText = (EditText)findViewById(R.id.activity_register_userMame);
        emailText = (EditText)findViewById(R.id.activity_register_email);
        passwordText = (EditText)findViewById(R.id.activity_register_password);
        registerButton = (Button)findViewById(R.id.activity_register_button);
        progressBar = findViewById(R.id.activity_register_progressBar);
        defaultRegisterButtonText = registerButton.getText().toString();

        registerButton.setOnClickListener(this);
        progressBar.setVisibility(View.GONE);


    }

    @Override
    public void onClick(View view) {
        if (view == registerButton) {
            progressBar.setVisibility(View.VISIBLE);
            registerButton.setText("");
            registerButton.setEnabled(false);
            usernameText.setEnabled(false);
            emailText.setEnabled(false);
            passwordText.setEnabled(false);

            bus.post(new Account.RegisterRequest(
                    usernameText.getText().toString(),
                    emailText.getText().toString(),
                    passwordText.getText().toString()));
        }
    }

    @Subscribe
    public void registerResponse(Account.RegisterResponse response){
        onUserResponse(response);
    }

    @Subscribe
    public void externalRegisterResponse(Account.RegisterWithExternalTokenResponse response){
        onUserResponse(response);
    }

    private void onUserResponse(Account.UserResponse response) {
        if (response.didSucceed()){
            setResult(RESULT_OK);
            finish();
            return;
        }

        response.showErrorToast(this);
        usernameText.setError(response.getPropertyErrror("userName"));
        emailText.setError(response.getPropertyErrror("email"));
        passwordText.setError(response.getPropertyErrror("password"));

        registerButton.setEnabled(true);
        usernameText.setEnabled(true);
        emailText.setEnabled(true);
        passwordText.setEnabled(true);

        progressBar.setVisibility(View.GONE);

        registerButton.setText(defaultRegisterButtonText);

    }


}
