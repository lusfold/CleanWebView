package com.lusfold.cleanwebview;

import java.lang.ref.WeakReference;

/**
 * Created by lusfold on 4/13/16.
 */
public abstract class WeakReferenceRunnable<T> implements Runnable {
    protected final WeakReference<T> mParam;

    protected WeakReferenceRunnable(T param) {
        mParam = new WeakReference(param);
    }
}
