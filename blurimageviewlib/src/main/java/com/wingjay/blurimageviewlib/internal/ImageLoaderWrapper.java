package com.wingjay.blurimageviewlib.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.wingjay.blurimageviewlib.Assert;
import com.wingjay.blurimageviewlib.loader.ImageLoader;
import com.wingjay.blurimageviewlib.loader.LoadingCallback;

/**
 * Created by seanzhou on 4/12/17.
 */

public class ImageLoaderWrapper implements ImageLoader {

    private final Context mContext;
    private com.nostra13.universalimageloader.core.ImageLoader imageLoader;
    private DisplayImageOptions displayImageOptions;

    private volatile boolean displayOptionsEnabled = false;

    public ImageLoaderWrapper(Context context) {
        Assert.checkNotNull(context);

        mContext = context.getApplicationContext();
        init();
    }

    private void init() {
        initUIL();
        initDisplayImageOptions();

        imageLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
    }

    private void initDisplayImageOptions() {
        displayImageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    private void initUIL() {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(mContext);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(config.build());
    }

    @Override
    public void load(String url, ImageView imageView, final LoadingCallback callback) {
        Assert.checkNotNull(imageView);
        Assert.checkNotNull(callback);

        if (displayOptionsEnabled) {
            imageLoader.displayImage(url, imageView, displayImageOptions, new ImageLoadingListener() {
                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                            callback.onFailed(imageUri, failReason.getType().toString());
                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            callback.onComplete(imageUri, loadedImage);
                        }

                        @Override
                        public void onLoadingStarted(String imageUri, View view) {
                            callback.onStarted(imageUri);
                        }

                        @Override
                        public void onLoadingCancelled(String imageUri, View view) {
                            callback.onCancelled(imageUri);
                        }
                    },
                    new ImageLoadingProgressListener() {
                        @Override
                        public void onProgressUpdate(String imageUri, View view, int current, int total) {
                            callback.onProgress(current, total);
                        }
                    });
        } else {
            imageLoader.loadImage(url, new ImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {
                            callback.onStarted(imageUri);
                        }

                        @Override
                        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                            callback.onFailed(imageUri, failReason.getType().toString());
                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            callback.onComplete(imageUri, loadedImage);
                        }

                        @Override
                        public void onLoadingCancelled(String imageUri, View view) {
                            callback.onCancelled(imageUri);
                        }
                    }
            );
        }
    }

    @Override
    public ImageLoader enableConfigOptions(boolean enabled) {
        displayOptionsEnabled = enabled;
        return this;
    }

    @Override
    public void cancelTask(ImageView view) {
        Assert.checkNotNull(view);
        imageLoader.cancelDisplayTask(view);
    }
}
