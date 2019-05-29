package com.llweiya.ysx.starchef.common.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.llweiya.ysx.starchef.R;

import timber.log.Timber;

public class ImageUtil {

    public static void showImage(Context context, ImageView imageView, String imageUrl) {
        showImageWithGlide(context, imageView, imageUrl, R.drawable.ic_avatar_normal);
    }

    private static void showImageWithGlide(Context context, ImageView imageView, Object imageSource,
                                           int placeHolderResourceId) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeHolderResourceId)
                .error(placeHolderResourceId)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .dontAnimate();
        try{
            Glide.with(context)
                    .load(imageSource)
                    .apply(options)
                    .into(imageView);
        } catch (Exception exception) {
            Timber.e(exception.getMessage());
        }


    }

}
