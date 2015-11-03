package com.spb.kbv.messageapp.services;

import com.spb.kbv.messageapp.infrastructure.MessageApplication;
import com.squareup.otto.Subscribe;

public class InMemoryAccountService {
    private MessageApplication application;

    public InMemoryAccountService (MessageApplication application){
        application.getBus().register(this);
        this.application = application;
    }

    @Subscribe
    public void updateProfile(Account.UpdateProfileRequest request){
        Account.UpdateProfileResponse response = new Account.UpdateProfileResponse();
        application.getBus().post(response);
    }

}
