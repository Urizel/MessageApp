package com.spb.kbv.messageapp.services;

import com.spb.kbv.messageapp.infrastructure.MessageApplication;

public class LiveContactService extends BaseLiveService {

    public LiveContactService(MessageApplication application, WebService api) {
        super(application, api);
    }
}
