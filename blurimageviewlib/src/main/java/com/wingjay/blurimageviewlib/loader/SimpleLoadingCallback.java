package com.wingjay.blurimageviewlib.loader;

import android.graphics.Bitmap;

/**
 * Created by seanzhou on 4/13/17.
 */

public abstract class SimpleLoadingCallback implements LoadingCallback {
    @Override
    public void onStarted(String imageUri) {

    }

    @Override
    public abstract void onFailed(String imageUri, String error);

    @Override
    public void onProgress(long downloaded, long total) {

    }

    @Override
    public abstract void onComplete(String imageUri, Bitmap bitmap);

    @Override
    public void onCancelled(String imageUri) {

    }
}
