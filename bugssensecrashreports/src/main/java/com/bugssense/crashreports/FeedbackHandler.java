package com.bugssense.crashreports;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FeedbackHandler {
    private final String COMMUNICATION_URL = "https://bugssense.com/feedbackAPI.php";
    public void sendFeedback(final String title, final String content, final Context context){
        final String language =  Locale.getDefault().toString();
        Log.d("Dil", "language: "+language);

        final String os = System.getProperty("os.version");        // OS version
        final String apilevel = android.os.Build.VERSION.SDK;     // API LEVEL
        final String device = android.os.Build.DEVICE;          // Device
        int verCode = 0;
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            verCode = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e1) {

        }
        final String model = android.os.Build.MODEL;            // Model



        RequestQueue queue = Volley.newRequestQueue(context);
        final int finalVerCode = verCode;
        StringRequest postRequest = new StringRequest(Request.Method.POST, COMMUNICATION_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("appID", context.getPackageName());
                params.put("language", language);
                params.put("title", title + "");
                params.put("context", content + "");
                params.put("appversion", finalVerCode + "");
                params.put("os", os + "");
                params.put("apilevel", apilevel + "");
                params.put("device", device + "");
                params.put("model", model + "");
                return params;
            }
        };
        queue.add(postRequest);

    }
}
