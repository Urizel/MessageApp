package com.spb.kbv.messageapp.services.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.spb.kbv.messageapp.infrastructure.User;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Message implements Parcelable{
    private int id;
    private Calendar createdAt;
    private String shortMessage;
    private String longMessage;
    private String messageUrl;
    private UserDetails otherUser;
    private boolean isFromUs;
    private boolean isSelected;
    private boolean isRead;

    public Message(
            int id,
            Calendar createdAt,
            String shortMessage,
            String longMessage,
            String messageUrl,
            UserDetails otherUser,
            boolean ifFromUs,
            boolean isRead) {
        this.id = id;
        this.createdAt = createdAt;
        this.shortMessage = shortMessage;
        this.longMessage = longMessage;
        this.messageUrl = messageUrl;
        this.otherUser = otherUser;
        this.isFromUs = ifFromUs;
        this.isRead = isRead;
    }

    private Message (Parcel parcel){
        id = parcel.readInt();
        createdAt = new GregorianCalendar();
        createdAt.setTimeInMillis(parcel.readLong());
        shortMessage = parcel.readString();
        longMessage = parcel.readString();
        messageUrl = parcel.readString();
        otherUser = (UserDetails) parcel.readParcelable(UserDetails.class.getClassLoader());
        isFromUs = parcel.readByte() == 1;
        isRead = parcel.readByte() == 1;
    }

    @Override
    public void writeToParcel(Parcel destination, int flags) {
        destination.writeInt(id);
        destination.writeLong(createdAt.getTimeInMillis());
        destination.writeString(shortMessage);
        destination.writeString(longMessage);
        destination.writeString(messageUrl);
        destination.writeParcelable(otherUser, 0);
        destination.writeByte((byte) (isFromUs ? 1 : 0));
        destination.writeByte((byte)(isRead ? 1 : 0));

    }

    @Override
    public int describeContents() {
        return 0;
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

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    private static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel source) {
            return new Message(source);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };
}
