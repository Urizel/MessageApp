package com.spb.kbv.messageapp.services.entities;

import java.util.Calendar;

public class ContactRequest {
    private boolean isFromUs;
    private UserDetails user;
    private String createdAt;

    public ContactRequest(boolean isFromUs, UserDetails user, String createdAt) {
        this.isFromUs = isFromUs;
        this.user = user;
        this.createdAt = createdAt;
    }

    public boolean isFromUs() {
        return isFromUs;
    }

    public UserDetails getUser() {
        return user;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
