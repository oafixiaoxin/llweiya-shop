package com.llweiya.ysx.starchef.common.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.llweiya.ysx.starchef.common.utils.ImageUtil;
import com.lzy.ninegrid.NineGridView;

public class NineGridViewImageLoader implements NineGridView.ImageLoader {
    @Override
    public void onDisplayImage(Context context, ImageView imageView, String url) {
        ImageUtil.showImage(context, imageView, url);
    }

    @Override
    public Bitmap getCacheImage(String url) {
        return null;
    }
}
