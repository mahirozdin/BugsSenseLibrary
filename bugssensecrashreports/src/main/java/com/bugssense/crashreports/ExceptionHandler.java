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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final String COMMUNICATION_URL = "https://bugssense.com/crashAPI.php";


    private Thread.UncaughtExceptionHandler defaultUEH;


    private Context context;

    /*
     * if any of the parameters is null, the respective functionality
     * will not be used
     */
    public ExceptionHandler(Thread.UncaughtExceptionHandler handler,Context context) {

        this.defaultUEH = handler;
        this.context = context;
    }

    public Map<String,String> getDeviceInfo(){
        final String os = System.getProperty("os.version");
        final String apilevel = android.os.Build.VERSION.SDK;
        final String device = android.os.Build.DEVICE;
        int verCode = 0;
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            verCode = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e1) {

        }
        final String model = android.os.Build.MODEL;
        Map<String, String> params = new HashMap<>();
        params.put("appID", context.getPackageName());
        Log.d(TAG, " GELDİ "+context.getPackageName());
        params.put("appversion", verCode + "");
        params.put("os", os + "");
        params.put("apilevel", apilevel + "");
        params.put("device", device + "");
        params.put("model", model + "");
        return params;

    }
    public void uncaughtException(Thread t, Throwable e) {

        Log.d(TAG, " GELDİ ");
        try {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            final String stacktrace = errors.toString();

            RequestQueue queue = Volley.newRequestQueue(context);

            StringRequest postRequest = new StringRequest(Request.Method.POST, COMMUNICATION_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            System.exit(2);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) { ;
                            System.exit(2);
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = getDeviceInfo();
                    params.put("error", stacktrace + "");
                    return params;
                }
            };
            queue.add(postRequest);
        }catch (Exception ext){
            ext.printStackTrace();
        }
        defaultUEH.uncaughtException(t, e);
    }
}