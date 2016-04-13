package com.lusfold.cleanwebview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebSettings;

/**
 * Created by lusfold on 4/13/16.
 */
public class SampleWebViewActivity extends Activity {
    private static final String TAG = "SampleWebViewActivity";
    private static final String TEST_URL = "http://cn.v2ex.com/";

    private CleanWebView mCleanWebView;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_webview);
        init();
        simulateRelease();
    }

    private void init() {
        mCleanWebView = (CleanWebView) findViewById(R.id.cleanWebView);
        WebSettings mSettings = mCleanWebView.getSettings();
        mSettings.setJavaScriptEnabled(true);
        mCleanWebView.loadUrl(TEST_URL);
        mHandler = new Handler();
    }

    private void simulateRelease() {
        mHandler.postDelayed(new WeakReferenceRunnable<Activity>(this) {
            @Override
            public void run() {
                Activity activity = mParam.get();
                if (activity != null) {
                    activity.finish();
                }
            }
        }, 4000);
    }

    @Override
    protected void onPause() {
        mCleanWebView.onPause();
        super.onPause();
    }


    @Override
    protected void onResume() {
        mCleanWebView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mCleanWebView.release();
        super.onDestroy();
    }
}
