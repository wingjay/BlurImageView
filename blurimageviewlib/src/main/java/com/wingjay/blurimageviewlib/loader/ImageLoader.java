package com.wingjay.blurimageviewlib.loader;

import android.widget.ImageView;

/**
 * Created by seanzhou on 4/12/17.
 */

public interface ImageLoader {

    /***
     * Whether enables configuration (e.g. cache) before loading the actual resource.
     * @param enabled
     * @return
     */
    ImageLoader enableConfigOptions(boolean enabled);

    /***
     * Loads the url resource for target <code>imageView</code>.
     * @param url
     * @param imageView
     * @param callback {@link LoadingCallback}
     */
    void load(String url, ImageView imageView, LoadingCallback callback);

    /***
     * Cancels the background task for the specified view if any.
     * @param view
     */
    void cancelTask(ImageView view);
}
