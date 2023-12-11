package com.example.receivesmsusingbr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Object[] pdus = (Object[]) bundle.get("pdus");

        SmsMessage message = SmsMessage.createFromPdu((byte[]) pdus[0]);

        Toast.makeText(context, "New Message Received", Toast.LENGTH_SHORT).show();
        Toast.makeText(context, "From: " + message.getOriginatingAddress() + "\n" + message.getMessageBody(), Toast.LENGTH_LONG).show();

    }
}
