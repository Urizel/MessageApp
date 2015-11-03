package com.spb.kbv.messageapp.fragments;

import android.app.Fragment;
import android.os.Bundle;

import com.spb.kbv.messageapp.infrastructure.MessageApplication;
import com.squareup.otto.Bus;

public abstract class BaseFragment extends Fragment {
    protected MessageApplication application;
    protected Bus bus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (MessageApplication)getActivity().getApplication();
        bus = application.getBus();
        bus.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }
}
