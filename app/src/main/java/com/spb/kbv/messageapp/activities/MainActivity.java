package com.spb.kbv.messageapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.spb.kbv.messageapp.R;
import com.spb.kbv.messageapp.services.Contacts;
import com.spb.kbv.messageapp.services.Messages;
import com.spb.kbv.messageapp.services.entities.ContactRequest;
import com.spb.kbv.messageapp.services.entities.Message;
import com.spb.kbv.messageapp.views.MainActivityAdapter;
import com.spb.kbv.messageapp.views.MainNavDrawer;
import com.squareup.otto.Subscribe;

import java.util.List;


public class MainActivity extends BaseAuthenticatedActivity implements View.OnClickListener, MainActivityAdapter.MainActivityListener {
    private MainActivityAdapter adapter;
    private List<Message> messages;
    private List<ContactRequest> contactRequests;

    @Override
    protected void onMessageAppCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Inbox");

        setNavDrawer(new MainNavDrawer(this));

        findViewById(R.id.activity_main_newMessageButton).setOnClickListener(this);

        adapter = new MainActivityAdapter(this, this);
        messages = adapter.getMessages();
        contactRequests = adapter.getContactRequests();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerView);
        recyclerView.setAdapter(adapter);

        if (isTablet) {
            GridLayoutManager manager = new GridLayoutManager(this, 2);
            recyclerView.setLayoutManager(manager);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position == 0) {
                        return 2;
                    }

                    if (contactRequests.size() > 0 && position == contactRequests.size() + 1) {
                        return 2;
                    }

                    return 1;
                }
            });
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        scheduler.invokeEveryMilliseconds(new Runnable() {
            @Override
            public void run() {
                onRefresh();
            }
        }, 1000 * 60 * 3);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.activity_main_newMessageButton){
            startActivity(new Intent(this, NewMessageActivity.class));
        }
    }

    @Override
    public void onRefresh() {
        swipeRefresh.setRefreshing(true);
        bus.post(new Messages.SearchMessagesRequest(false, true));
        bus.post(new Contacts.GetContactRequestRequest(false));
    }

    @Subscribe
    public void inMessagesLoaded(final Messages.SearchMessageResponse response){
        scheduler.invokeOnResume(response.getClass(), new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(false);

                if (!response.didSucceed()) {
                    response.showErrorToast(MainActivity.this);
                    return;
                }
                messages.clear();
                messages.addAll(response.messages);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Subscribe
    public void onContactRequestsLoaded (final Contacts.GetContactRequestResponse response){
        scheduler.invokeOnResume(response.getClass(), new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(false);

                if (!response.didSucceed()){
                    response.showErrorToast(MainActivity.this);
                    return;
                }

                contactRequests.clear();
                contactRequests.addAll(response.requests);
            }
        });
    }

    @Override
    public void onMessageClicked(Message message) {

    }

    @Override
    public void onContactRequestClicked(ContactRequest request, int position) {

    }
}
