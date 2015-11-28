package com.spb.kbv.messageapp.receivers;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.spb.kbv.messageapp.R;
import com.spb.kbv.messageapp.activities.MainActivity;
import com.spb.kbv.messageapp.activities.MessageActivity;
import com.spb.kbv.messageapp.infrastructure.Auth;
import com.spb.kbv.messageapp.infrastructure.MessageApplication;
import com.spb.kbv.messageapp.services.Events;
import com.squareup.otto.Bus;

public class NotificationReceiver extends BroadcastReceiver {
    private static final String TAG = "NotificationReceiver";

    private Auth auth;
    private MessageApplication application;

    @Override
    public void onReceive(Context context, Intent intent) {
        application = (MessageApplication) context.getApplicationContext();
        auth = application.getAuth();
        Bus bus = application.getBus();

        try {
            int operation = Integer.parseInt(intent.getStringExtra("operation"));
            int type = Integer.parseInt(intent.getStringExtra("type"));
            int entityId = Integer.parseInt(intent.getStringExtra("entityId"));
            int entityOwnerId = Integer.parseInt(intent.getStringExtra("entityOwnerId"));
            String entityOwnerName = intent.getStringExtra("entityOwnerName");

            Events.OnNotificationReceivedEvent event = new Events.OnNotificationReceivedEvent(
                    operation, type, entityId, entityOwnerId, entityOwnerName);

            if (type == Events.ENTITY_CONTACT) {
                sendContactNotification(event);
            } else if (type == Events.ENTITY_CONTACT_REQUEST) {
                sendContactRequestNotification(event);
            } else if (type == Events.ENTITY_MESSAGE) {
                sendMessageNotification(event);
            }

            bus.post(event);
        } catch (NumberFormatException e) {
            Log.e(TAG, "Error parsing message", e);
        }

        setResultCode(Activity.RESULT_OK);
    }

    private void sendContactNotification(Events.OnNotificationReceivedEvent event) {
    }

    private void sendContactRequestNotification(Events.OnNotificationReceivedEvent event) {
        if (event.operationType == Events.OPERATION_DELETED || event.entityId == auth.getUser().getId()) {
            return;
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(application)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(event.entityOwnerName + " sent you a contact request")
                .setContentText("Click here to view it");

        sendNotification(event.entityId, builder, new Intent(application, MainActivity.class));
    }

    private void sendMessageNotification(Events.OnNotificationReceivedEvent event) {
        if (event.operationType == Events.OPERATION_DELETED || event.entityOwnerId == auth.getUser().getId()) {
            return;
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(application)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(event.entityOwnerName + " sent you a messgae request")
                .setContentText("Click here to view it");

        Intent intent = new Intent(application, MessageActivity.class);
        intent.putExtra(MessageActivity.EXTRA_MESSAGE_ID, event.entityId);
        sendNotification(event.entityId, builder, intent);
    }

    private void sendNotification(int entityId, NotificationCompat.Builder builder, Intent intent) {
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(application);
        stackBuilder.addParentStack(MessageActivity.class);
        stackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager) application.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(entityId, notification);
    }
}