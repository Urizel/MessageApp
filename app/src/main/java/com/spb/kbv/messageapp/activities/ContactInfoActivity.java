package com.spb.kbv.messageapp.activities;

import android.os.Bundle;

import com.spb.kbv.messageapp.R;

public class ContactInfoActivity extends BaseAuthenticatedActivity{
    public static final String EXTRA_USER_DETAILS = "EXTRA_USER_DETAILS";

    @Override
    protected void onMessageAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_contacts_info);
    }
}
