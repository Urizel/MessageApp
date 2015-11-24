package com.spb.kbv.messageapp.services;

import com.spb.kbv.messageapp.infrastructure.MessageApplication;

public class LiveMessageService extends BaseLiveService {

    public LiveMessageService(MessageApplication application, WebService api) {
        super(application, api);
    }
}
