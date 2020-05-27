package com.llweiya.ysx.starchef.business.community.view.adapter;

import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.community.model.FavoriteItemModel;
import com.llweiya.ysx.starchef.common.utils.ImageUtil;
import com.llweiya.ysx.starchef.common.utils.UIUtil;

public class FavoriteItemAdapter extends BaseQuickAdapter<FavoriteItemModel, BaseViewHolder> {
    public FavoriteItemAdapter() {
        super(R.layout.item_favorite_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, FavoriteItemModel item) {
        LinearLayout.LayoutParams imageParams = (LinearLayout.LayoutParams)helper.getView(R.id.image).getLayoutParams();
        if (helper.getAdapterPosition() == 0
                || helper.getAdapterPosition() == 5
                || helper.getAdapterPosition() == 9
                || helper.getAdapterPosition() == 18) {
            imageParams.height = UIUtil.dip2pixel(mContext, 180);
        } else {
            imageParams.height = UIUtil.dip2pixel(mContext, 150);
        }
        helper.getView(R.id.image).setLayoutParams(imageParams);
        ImageUtil.showImage(mContext, helper.getView(R.id.image), item.threadImg);
        ImageUtil.showImage(mContext, helper.getView(R.id.image_avatar), item.userAvatar);
        helper.setImageResource(R.id.image_favorite, item.isLike ? R.drawable.icon_heart_red : R.drawable.icon_heart_empty);
        helper.setText(R.id.text, item.threadTitle);
        helper.setText(R.id.txt_user_name, item.userNickname);
        helper.setText(R.id.txt_favorite_count, String.valueOf(item.likeCount));
    }
}
