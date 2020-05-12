package com.llweiya.ysx.starchef.business.user.view.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.user.model.NinePictureItemViewModel;
import com.llweiya.ysx.starchef.common.utils.ImageUtil;
import com.llweiya.ysx.starchef.common.utils.Utility;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.List;

public class NinePictureTestAdapter extends BaseQuickAdapter<NinePictureItemViewModel, BaseViewHolder> {
    public NinePictureTestAdapter() {
        super(R.layout.item_nine_picture);
    }

    @Override
    protected void convert(BaseViewHolder helper, NinePictureItemViewModel item) {
        ImageUtil.showImage(mContext, helper.getView(R.id.image_avatar), item.avatarUrl);
        helper.setText(R.id.txt_user_name, item.userName);
        if (TextUtils.isEmpty(item.content)) {
            helper.setGone(R.id.txt_content, false);
        } else {
            helper.setText(R.id.txt_content, item.content);
            helper.setGone(R.id.txt_content, true);
        }

        if (Utility.listHasElement(item.pictureUrls)) {
            List<ImageInfo> imageList = new ArrayList<>();
            for (String url : item.pictureUrls) {
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setThumbnailUrl(url);
                imageInfo.setBigImageUrl(url);
                imageList.add(imageInfo);
            }
            NineGridView nineGridView = helper.getView(R.id.nine_view);
            nineGridView.setAdapter(new NineGridViewClickAdapter(mContext, imageList));
            helper.setGone(R.id.nine_view, true);
        } else {
            helper.setGone(R.id.nine_view, false);
        }
    }
}
