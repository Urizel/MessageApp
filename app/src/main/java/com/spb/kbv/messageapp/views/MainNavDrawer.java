package com.spb.kbv.messageapp.views;

import android.view.View;
import android.widget.Toast;

import com.spb.kbv.messageapp.R;
import com.spb.kbv.messageapp.activities.BaseActivity;
import com.spb.kbv.messageapp.activities.MainActivity;

public class MainNavDrawer extends NavDrawer {
    public MainNavDrawer(final BaseActivity activity) {
        super(activity);

        addItem(new ActivityNavDrawerItem(
                MainActivity.class,
                "Inbox",
                null,
                R.drawable.ic_action_unread,
                0));

        addItem(new ActivityNavDrawerItem(
                MainActivity.class,
                "Logout",
                null,
                R.drawable.ic_action_backspace,
                0) {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "You have logged out", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
