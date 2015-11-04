package com.spb.kbv.messageapp.services;

import com.spb.kbv.messageapp.infrastructure.MessageApplication;
import com.squareup.otto.Subscribe;

public class InMemoryAccountService extends BaseInMemoryService {
    private MessageApplication application;

    public InMemoryAccountService (MessageApplication application){
        super(application);
    }

    @Subscribe
    public void updateProfile(Account.UpdateProfileRequest request){
        Account.UpdateProfileResponse response = new Account.UpdateProfileResponse();
        if (request.displayName.equals("b"))
            response.setPropertyError("displayName", "ERROR");

        postDelayed(response);
    }

    @Subscribe
    public void updateAvatar(Account.ChangeAvatarRequest request){
        postDelayed(new Account.ChangeAvatarResponse());
    }

    @Subscribe
    public void changePassword(Account.ChangePasswordRequest request){
        Account.ChangePasswordResponse response = new Account.ChangePasswordResponse();

        if (!request.newPassword.equals(request.confirmNewPassword)){
            response.setPropertyError("confirmNewPassword", "Passwords must match");
        }

        if (request.newPassword.length() < 3){
            response.setPropertyError("newPassword", "Password must be longer than 3 characters");
        }

        postDelayed(response);
    }

}
