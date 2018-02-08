package com.jm.newvista.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * Created by Johnny on 2/8/2018.
 */

public class GlideBlurTransformation extends BitmapTransformation {
    private Context context;

    public GlideBlurTransformation(Context context) {
        this.context = context;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return BlurBitmapUtil.instance().blurBitmap(context, toTransform, 25,outWidth,outHeight);
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }
}
