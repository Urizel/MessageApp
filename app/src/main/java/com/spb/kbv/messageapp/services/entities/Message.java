package com.spb.kbv.messageapp.services.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

public class Message implements Parcelable{
    private int id;
    private Calendar createdAt;
    private String shortMessage;
    private String longMessage;
    private String messageUrl;
    private UserDetails otherUser;
    private boolean isFromUs;
    private boolean isRead;
    private boolean isSelected;

    public Message(
            int id,
            Calendar calendar,
            String shortMessage,
            String longMessage,
            String messageUrl,
            UserDetails otherUser,
            boolean ifFromUs,
            boolean isRead) {
        this.id = id;
        this.createdAt = calendar;
        this.shortMessage = shortMessage;
        this.longMessage = longMessage;
        this.messageUrl = messageUrl;
        this.otherUser = otherUser;
        this.isFromUs = ifFromUs;
        this.isRead = isRead;
    }

    public int getId() {
        return id;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public String getShortMessage() {
        return shortMessage;
    }

    public String getLongMessage() {
        return longMessage;
    }

    public String getMessageUrl() {
        return messageUrl;
    }

    public UserDetails getOtherUser() {
        return otherUser;
    }

    public boolean isFromUs() {
        return isFromUs;
    }

    public boolean isRead() {
        return isRead;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    private static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[0];
        }
    };
}
