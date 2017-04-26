package com.mardyoe.smstodb.controller.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mardyoe.smstodb.controller.util.Constant;
import com.mardyoe.smstodb.controller.util.VolleyHelper;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zarulizham on 26/04/2017.
 */

public class SmsListener extends BroadcastReceiver implements
        Response.ErrorListener,
        Response.Listener<JSONObject> {

    Bundle bundle;
    SmsMessage currentSMS;
    private String senderNo, message, issuerBank = "";
    Context context;
    int retry = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdu_Objects = (Object[]) bundle.get("pdus");
                if (pdu_Objects != null) {

                    for (Object aObject : pdu_Objects) {
                        currentSMS = getIncomingMessage(aObject, bundle);
                        senderNo = currentSMS.getDisplayOriginatingAddress();
                        message = currentSMS.getDisplayMessageBody();
                    }
                    this.abortBroadcast();
                }
            }
        }

        if (findMatcher(message)) {
            sendMessage(senderNo, message);
        }

    }

    private SmsMessage getIncomingMessage(Object aObject, Bundle bundle) {
        SmsMessage currentSMS;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String format = bundle.getString("format");
            currentSMS = SmsMessage.createFromPdu((byte[]) aObject, format);
        } else {
            currentSMS = SmsMessage.createFromPdu((byte[]) aObject);
        }

        return currentSMS;
    }

    public void sendMessage(String sender, String message) {
        VolleyHelper volley = new VolleyHelper(context, Constant.baseUrl);

        JSONObject request = new JSONObject();

        try {
            request.put("sender", sender);
            request.put("message", message);
            request.put("bank_name", issuerBank);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        volley.post("save-sms.php", request, this, this);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("Error", error.toString());
        if (++retry < 5) {
            sendMessage(senderNo, message);
        }
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(context, "Your new SMS has been save to database", Toast.LENGTH_SHORT).show();
    }

    public boolean findMatcher(String text) {
        boolean found = false;
        List<String> tokens = new ArrayList<>();
        tokens.add("CIMB");
        tokens.add("BIMB");
        tokens.add("M2U");
        text = text.toUpperCase();
        Log.e("TExt", text);

        String patternString = "\\b(" + StringUtils.join(tokens, "|") + ")\\b";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            found = true;
            issuerBank += matcher.group(1);
        }

        return found;
    }
}