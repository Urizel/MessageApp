package com.spb.kbv.messageapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.spb.kbv.messageapp.R;
import com.spb.kbv.messageapp.services.Messages;
import com.spb.kbv.messageapp.services.entities.Message;
import com.spb.kbv.messageapp.services.entities.UserDetails;
import com.spb.kbv.messageapp.views.MessagesAdapter;

import java.util.ArrayList;

public class ContactInfoActivity extends BaseAuthenticatedActivity implements MessagesAdapter.OnMessageClickListener {
    public static final String EXTRA_USER_DETAILS = "EXTRA_USER_DETAILS";

    private UserDetails userDetails;
    private MessagesAdapter adapter;
    private ArrayList<Message> messages;
    private View progressFrame;

    @Override
    protected void onMessageAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_contacts_info);

        progressFrame = findViewById(R.id.activity_contacts_info_progressFrame);
        userDetails = getIntent().getParcelableExtra(EXTRA_USER_DETAILS);
        if (userDetails == null){
            userDetails = new UserDetails(1, true, "A contact", "a_contact", "http://www.gravatar.com/avatar/1.jpg");
        }

        adapter = new MessagesAdapter(this, this);
        messages = adapter.getMessages();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.activity_contacts_info_messages);
        if  (isTablet){
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        recyclerView.setAdapter(adapter);
        getSupportActionBar().setTitle(userDetails.getDisplayName());
        toolbar.setNavigationIcon(R.drawable.ic_ab_close);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        scheduler.postEveryMilliseconds(new Messages.SearchMessagesRequest(userDetails.getId(), true, true),1000 * 60 * 3);


    }

    @Override
    public void onMessageClicked(Message message) {
        Intent intent = new Intent(this, MessageActivity.class);
        intent.putExtra(MessageActivity.EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
