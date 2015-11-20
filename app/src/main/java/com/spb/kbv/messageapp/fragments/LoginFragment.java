package com.spb.kbv.messageapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.spb.kbv.messageapp.R;
import com.spb.kbv.messageapp.services.Account;
import com.squareup.otto.Subscribe;

public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private Button loginButton;
    private Callbacks callbacks;
    private View progressBar;
    private EditText usernameText;
    private EditText passwordText;
    private String defaultLoginButtonText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginButton = (Button)view.findViewById(R.id.fragment_login_loginButton);
        loginButton.setOnClickListener(this);

        progressBar = view.findViewById(R.id.fragment_login_progress);
        usernameText = (EditText)view.findViewById(R.id.fragment_login_username);
        passwordText = (EditText)view.findViewById(R.id.fragment_login_password);
        defaultLoginButtonText = loginButton.getText().toString();
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == loginButton){
            progressBar.setVisibility(View.VISIBLE);
            loginButton.setText("");
            loginButton.setEnabled(false);
            usernameText.setEnabled(false);
            passwordText.setEnabled(false);


            bus.post(new Account.LoginWithUsernameRequest(
                    usernameText.getText().toString(),
                    passwordText.getText().toString()));
        }
    }

    @Subscribe
    public void onLoginWithUserName (Account.LoginWithUsernameResponse respones){
        respones.showErrorToast(getActivity());

        if (respones.didSucceed()){
            callbacks.onLoggedIn();
            return;
        }

        usernameText.setError(respones.getPropertyError("userName"));
        usernameText.setEnabled(true);

        passwordText.setError(respones.getPropertyError("password"));
        passwordText.setEnabled(true);

        loginButton.setEnabled(true);

        progressBar.setVisibility(View.GONE);
        loginButton.setText(defaultLoginButtonText);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callbacks = (Callbacks)activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    public interface Callbacks {
        void onLoggedIn();
    }

}
