package com.spb.kbv.messageapp.activities;

import android.os.Bundle;

import com.spb.kbv.messageapp.R;

public class AddContactActivity extends BaseAuthenticatedActivity {
    public static final String RESULT_CONTACT = "RESULT_CONTACT";
    @Override
    protected void onMessageAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_contact);
    }
}
