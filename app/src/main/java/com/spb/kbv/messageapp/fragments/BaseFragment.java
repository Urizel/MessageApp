package com.spb.kbv.messageapp.fragments;

import android.app.Fragment;
import android.os.Bundle;

import com.spb.kbv.messageapp.infrastructure.MessageApplication;

public abstract class BaseFragment extends Fragment {
    MessageApplication application;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (MessageApplication)getActivity().getApplication();
    }
}
