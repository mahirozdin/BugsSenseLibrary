package com.videodownloader.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bugssense.crashreports.ExceptionHandler;
import com.bugssense.crashreports.FeedbackHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FeedbackHandler fh = new FeedbackHandler();
        fh.sendFeedback("title Test","Content Test","ozdinmt@gmail.com",this);
        Thread.UncaughtExceptionHandler defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
        if (!(defaultUEH instanceof ExceptionHandler)) {
            Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(defaultUEH,MainActivity.this));
        }

        int a = 1/0;

    }
}
