package com.spb.kbv.messageapp.views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.spb.kbv.messageapp.R;
import com.spb.kbv.messageapp.activities.BaseActivity;
import com.spb.kbv.messageapp.activities.ContactActivity;
import com.spb.kbv.messageapp.activities.MainActivity;
import com.spb.kbv.messageapp.activities.ProfileActivity;
import com.spb.kbv.messageapp.activities.SentMessagesActivity;
import com.spb.kbv.messageapp.infrastructure.User;
import com.spb.kbv.messageapp.services.Account;
import com.squareup.otto.Subscribe;

public class MainNavDrawer extends NavDrawer {
    private final TextView displayNameText;
    private final ImageView avatarImage;

    public MainNavDrawer(final BaseActivity activity) {
        super(activity);

        addItem(new ActivityNavDrawerItem(MainActivity.class,
                "Inbox",
                null,
                R.drawable.ic_action_unread,
                R.id.include_main_nav_drawer_topItems));

        addItem(new ActivityNavDrawerItem(
                SentMessagesActivity.class,
                "Sent Messages",
                null,
                R.drawable.ic_action_send_now,
                R.id.include_main_nav_drawer_topItems));

        addItem(new ActivityNavDrawerItem(ContactActivity.class,
                "Contacts",
                null,
                R.drawable.ic_action_group,
                R.id.include_main_nav_drawer_topItems));

        addItem(new ActivityNavDrawerItem(ProfileActivity.class,
                "Profile",
                null,
                R.drawable.ic_action_person,
                R.id.include_main_nav_drawer_topItems));


        addItem(new BasicNavDrawerItem(
                "Logout",
                null,
                R.drawable.ic_action_backspace,
                R.id.include_main_nav_drawer_bottomItems) {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "You have logged out", Toast.LENGTH_SHORT).show();
            }
        });

        displayNameText = (TextView)navDrawerView.findViewById(R.id.include_main_nav_drawer_displayName);
        avatarImage = (ImageView)navDrawerView.findViewById(R.id.include_main_nav_drawer_avatar);

        User loggedInUser = activity.getMessageAppApplication().getAuth().getUser();
        displayNameText.setText(loggedInUser.getUserName());
    }

    @Subscribe
    public void onUserDetailUpdate(Account.UserDetailsUpdateEvent event){
        displayNameText.setText(event.user.getDisplayName());
    }
}
