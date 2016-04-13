package com.lusfold.cleanwebview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;

/**
 * Created by lusfold on 4/13/16.
 */
public class CleanWebView extends WebView {
    private static final String TAG = "CleanWebView";
    private static final String ABOUT_BLANK = "about:blank";

    public CleanWebView(Context context) {
        this(context, null);
    }

    public CleanWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CleanWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void release() {
        Log.d(TAG, "start release webView");
        try {
            this.loadUrl(ABOUT_BLANK);
            this.reload();

            View content = this;
            ViewParent parent = content.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(content);
            }

            this.stopLoading();
            content.clearAnimation();
            content.clearFocus();
            this.setDownloadListener(null);
            this.setWebChromeClient(null);
            this.setWebViewClient(null);
            this.clearHistory();
            this.clearSslPreferences();
            content.destroyDrawingCache();
            this.freeMemory();

            this.destroy();
        } catch (Throwable t) {
            Log.d(TAG, "release webView cause a exception " + t.toString());
        }
    }
}
