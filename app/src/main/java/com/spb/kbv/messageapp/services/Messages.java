package com.spb.kbv.messageapp.services;

import com.spb.kbv.messageapp.infrastructure.ServiceResponse;
import com.spb.kbv.messageapp.services.entities.Message;

import java.util.List;

public final class Messages {
    private Messages(){
    }

    public static class DeleteMessageRequest {
        public int messageId;

        public DeleteMessageRequest(int messageId) {
            this.messageId = messageId;
        }
    }

    public static class DeleteMessageResponse extends ServiceResponse{
        public int messageId;
    }

    public static class SearchMessagesRequest {
        public int fromContactId;
        public boolean includeSentMessages;
        public boolean includeReceivedMessages;

        public SearchMessagesRequest(int fromContactId, boolean includeSentMessages, boolean includeReceivedMessages) {
            this.fromContactId = fromContactId;
            this.includeSentMessages = includeSentMessages;
            this.includeReceivedMessages = includeReceivedMessages;
        }

        public SearchMessagesRequest(boolean includeSentMessages, boolean includeReceivedMessages) {
            this.includeSentMessages = includeSentMessages;
            this.includeReceivedMessages = includeReceivedMessages;
        }
    }

    public static class SearchMessageResponse extends ServiceResponse {
        public List<Message> messages;
    }




}
