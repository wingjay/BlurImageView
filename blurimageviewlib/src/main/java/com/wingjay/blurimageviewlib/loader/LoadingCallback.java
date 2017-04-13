package com.wingjay.blurimageviewlib.loader;

import android.graphics.Bitmap;

/**
 * Created by seanzhou on 4/12/17.
 */

public interface LoadingCallback {
    void onStarted(String imageUri);

    void onFailed(String imageUri, String error);

    void onProgress(long downloaded, long total);

    void onComplete(String imageUri, Bitmap bitmap);

    void onCancelled(String imageUri);
}
