package com.spb.kbv.messageapp.services;

import com.spb.kbv.messageapp.infrastructure.ServiceResponse;
import com.spb.kbv.messageapp.services.entities.Message;

import java.util.List;

public final class Messages {
    private Messages(){
    }

    public class DeleteMessageRequest {
        public int messageId;

        public DeleteMessageRequest(int messageId) {
            this.messageId = messageId;
        }
    }

    public class DeleteMessageResponse extends ServiceResponse{
    }

    public class SearchMessagesRequest {
        public String fromContactId;
        public boolean includeSentMessages;
        public boolean includeReceivedMessages;

        public SearchMessagesRequest(String fromContactId, boolean includeSentMessages, boolean includeReceivedMessages) {
            this.fromContactId = fromContactId;
            this.includeSentMessages = includeSentMessages;
            this.includeReceivedMessages = includeReceivedMessages;
        }

        public SearchMessagesRequest(boolean includeSentMessages, boolean includeReceivedMessages) {
            this.includeSentMessages = includeSentMessages;
            this.includeReceivedMessages = includeReceivedMessages;
        }
    }

    public class SearchMessageResponse extends ServiceResponse {
        public List<Message> messages;
    }




}
