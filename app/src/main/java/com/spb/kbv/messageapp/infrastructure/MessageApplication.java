package com.spb.kbv.messageapp.infrastructure;

import android.app.Application;
import android.net.Uri;
import android.util.Log;

import com.spb.kbv.messageapp.services.Module;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.otto.Bus;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class MessageApplication extends Application{
    public static final Uri API_ENDPOINT = Uri.parse(/*"http://yora-playground.3dbuzz.com"*/"http://192.168.0.101:8888");
    public static final String TOKEN = "f2b36dd2a4d84bff90e161de6323efbe";
    private Auth auth;
    private Bus bus;
    private Picasso authedPicasso;

    public MessageApplication(){
        bus = new Bus();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        auth = new Auth(this);
        createAuthedPicasso();
        Module.register(this);
    }

    private void createAuthedPicasso(){
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Log.d("myLogs", "token in createPicasso " + getAuth().getAuthToken());
                Request newRequest = chain.request().newBuilder()
                        .addHeader(/*"Authorization", "Bearer " + getAuth().getAuthToken()*/
                                "x-access-token", "Bearer " + getAuth().getAuthToken())

                        /*.addHeader("X-Student", TOKEN)*/
                        .build();

                return chain.proceed(newRequest);
            }
        });

        authedPicasso = new Picasso.Builder(this)
                .downloader(new OkHttpDownloader(client))
                .build();
    }

    public Picasso getAuthedPicasso(){
        return authedPicasso;
    }

    public Auth getAuth() {
        return auth;
    }

    public Bus getBus(){
        return bus;
    }
}
