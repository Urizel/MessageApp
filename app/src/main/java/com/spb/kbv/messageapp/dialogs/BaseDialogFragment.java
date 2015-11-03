package com.spb.kbv.messageapp.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;

import com.spb.kbv.messageapp.infrastructure.MessageApplication;
import com.squareup.otto.Bus;

public class BaseDialogFragment extends DialogFragment{
    protected MessageApplication application;
    protected Bus bus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (MessageApplication)getActivity().getApplication();
        bus = application.getBus();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }
}
