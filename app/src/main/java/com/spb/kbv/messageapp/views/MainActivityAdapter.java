package com.spb.kbv.messageapp.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.spb.kbv.messageapp.activities.BaseActivity;
import com.spb.kbv.messageapp.services.entities.ContactRequest;
import com.spb.kbv.messageapp.services.entities.Message;

import java.util.ArrayList;
import java.util.List;

public class MainActivityAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE = 1;
    private static final int VIEW_TYPE_CONTACT_REQUEST = 2;
    private static final int VIEW_TYPE_HEADER = 3;

    private List<Message> messages;
    private List<ContactRequest> contactRequests;
    private MainActivityListener listener;
    private LayoutInflater inflater;
    private BaseActivity activity;

    public MainActivityAdapter(MainActivityListener listener, BaseActivity activity) {
        this.listener = listener;
        this.activity = activity;
        inflater = activity.getLayoutInflater();
        messages = new ArrayList<>();
        contactRequests = new ArrayList<>();
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<ContactRequest> getContactRequests() {
        return contactRequests;
    }

    @Override
    public int getItemViewType(int position) {
        if (contactRequests.size() > 0){
            if (position == 0){
                return VIEW_TYPE_HEADER;
            }

            position--;

            if (position < contactRequests.size()){
                return VIEW_TYPE_CONTACT_REQUEST;
            }

            position -= contactRequests.size();
        }

        if (messages.size() > 0){
            if (position == 0){
                return VIEW_TYPE_HEADER;
            }

            position--;

            if (position < messages.size()){
                return VIEW_TYPE_MESSAGE;
            }

            position -= contactRequests.size();
        }

        throw new IllegalArgumentException("We are being asked for an item type from position " + position +
        ", though we have no such item.");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface MainActivityListener {
        void onMessageClicked(Message message);
        void onContactRequestClicked(ContactRequest request, int position);
    }
}
