package com.lusfold.cleanwebview;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mHandler = new Handler();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(new WeakReferenceRunnable<Activity>(this) {
            @Override
            public void run() {
                Activity activity = mParam.get();
                if (activity != null) {
                    Intent intent = new Intent(activity, SampleWebViewActivity.class);
                    activity.startActivity(intent);
                }
            }
        }, 1000);
    }
}
