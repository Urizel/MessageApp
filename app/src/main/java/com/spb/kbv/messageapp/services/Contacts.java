package com.spb.kbv.messageapp.services;

import com.spb.kbv.messageapp.infrastructure.ServiceResponse;
import com.spb.kbv.messageapp.services.entities.ContactRequest;
import com.spb.kbv.messageapp.services.entities.UserDetails;

import java.util.List;

public final class Contacts {
    private Contacts () {}

    public static class GetContactRequestRequest {
        public boolean fromUs;

        public GetContactRequestRequest(boolean fromUs) {
            this.fromUs = fromUs;
        }
    }

    public static class GetContactRequestResponse extends ServiceResponse {
        public List<ContactRequest> requests;
    }

    public static class GetContactRequest {
        public boolean includePendingContacts;

        public GetContactRequest(boolean includePendingContacts) {
            this.includePendingContacts = includePendingContacts;
        }
    }

    public static class GetContactResponse extends ServiceResponse{
        public List<UserDetails> contacts;
    }

    public static class SendContactRequestRequest{
        public int userId;

        public SendContactRequestRequest(int userId) {
            this.userId = userId;
        }
    }

    public static class SendContactRequestResponse extends ServiceResponse{
    }

    public static class RespondToContactRequestRequest {
        public int contactRequestId;
        public boolean accept;

        public RespondToContactRequestRequest(int contactRequestId, boolean accept) {
            this.contactRequestId = contactRequestId;
            this.accept = accept;
        }
    }

    public static class RespondToContactRequestResponse extends ServiceResponse{
    }
}
