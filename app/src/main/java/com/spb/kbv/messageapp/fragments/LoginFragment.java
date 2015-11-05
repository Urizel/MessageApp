package com.spb.kbv.messageapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spb.kbv.messageapp.R;
import com.spb.kbv.messageapp.services.Account;
import com.squareup.otto.Subscribe;

public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private View loginButton;
    private Callbacks callbacks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginButton = view.findViewById(R.id.frafment_login_loginButton);
        loginButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == loginButton){
            progressBar.setVisibility(View.VISIBLE);
            loginButton.setText("");
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

        usernameText.setError(respones.getPropertyErrror("userName"));
        usernameText.setEnabled(true);

        passwordText.setError(respones.getPropertyErrror("password"));
        passwordText.setEnabled(true);

        progressBar.setVisibiliti(View.GONE);
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
