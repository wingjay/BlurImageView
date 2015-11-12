package com.wingjay.blurimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * This imageView can show blur image.
 * Then it will be expanded to automatically display one image with two styles
 * one is small and blurry, another is the origin image,So here are two urls for these two images.
 */
public class BlurImageView extends ImageView {

    private Context mContext;

    private int mBlurFactor = 8;
    private String mBlurImageUrl, mOriginImageUrl;

    public BlurImageView(Context context) {
        this(context, null);
    }

    public BlurImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BlurImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mContext = getContext();
        //scaleType
        super.setScaleType(ScaleType.FIT_CENTER);
    }

    private Target blurTarget = new SimpleTarget() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            setImageBitmap(getBlurBitmap(bitmap));
        }
    };

    private Target fullTarget = new SimpleTarget() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            setImageBitmap(getBlurBitmap(bitmap));
            Picasso.with(mContext).load(mOriginImageUrl).placeholder(getDrawable()).into(BlurImageView.this);
        }
    };

    @Override
    public void setScaleType(ScaleType scaleType) {
        //forbid change scaleType
    }

    /**
     * This method will fetch bitmap from resource and make it blurry, display
     * @param blurImageRes the image resource id which is needed to be blurry
     */
    public void setBlurImageByRes(int blurImageRes) {
        buildDrawingCache();
        Bitmap blurBitmap = FastBlurUtil.doBlur(getDrawingCache(), mBlurFactor, true);
        setImageBitmap(blurBitmap);
    }

    /**
     * This image won't be blurry.
     * @param originImageRes The origin image resource id.
     */
    public void setOriginImageByRes(int originImageRes) {
        Bitmap originBitmap = BitmapFactory.decodeResource(mContext.getResources(), originImageRes);
        setImageBitmap(originBitmap);
    }

    public void setBlurImageByUrl(String blurImageUrl) {
        mBlurImageUrl = blurImageUrl;
        cancelImageRequestForSafty(blurTarget);
        Picasso.with(mContext).load(blurImageUrl).placeholder(getDrawable()).into(blurTarget);
    }

    public void setOriginImageByUrl(String originImageUrl) {
        mOriginImageUrl = originImageUrl;
        Picasso.with(mContext).load(originImageUrl).placeholder(getDrawable()).into(this);
    }

    public void setFullImageByUrl(String blurImageUrl, String originImageUrl) {
        mBlurImageUrl = blurImageUrl;
        mOriginImageUrl = originImageUrl;
        cancelImageRequestForSafty(fullTarget);
        Picasso.with(mContext).load(blurImageUrl).into(fullTarget);
    }

    private Bitmap getBlurBitmap(Bitmap loadedBitmap) {
        // make this bitmap mutable
        loadedBitmap = loadedBitmap.copy(loadedBitmap.getConfig(), true);
        return FastBlurUtil.doBlur(loadedBitmap, getBlurFactor(), true);
    }

    private int getBlurFactor() {
        return mBlurFactor;
    }

    public void setBlurFactor(int blurFactor) {
        if (blurFactor < 0) {
            throw new IllegalArgumentException("blurFactor must not be less than 0");
        }
        mBlurFactor = blurFactor;
    }

    private void cancelImageRequestForSafty(Target target) {
        Picasso.with(mContext).cancelRequest(target);
    }

    public void cancelImageLoadForSafty() {
        cancelImageRequestForSafty(blurTarget);
        cancelImageRequestForSafty(fullTarget);
    }

    public void clear() {
        setImageBitmap(null);
        cancelImageLoadForSafty();
    }

    private class SimpleTarget implements Target {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    }

}
