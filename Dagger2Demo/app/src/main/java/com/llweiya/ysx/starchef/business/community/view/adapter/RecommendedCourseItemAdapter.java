package com.llweiya.ysx.starchef.business.community.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.community.model.UserCourseViewModel;
import com.llweiya.ysx.starchef.common.utils.ImageUtil;

public class RecommendedCourseItemAdapter extends BaseQuickAdapter<UserCourseViewModel, BaseViewHolder> {
    public RecommendedCourseItemAdapter() {
        super(R.layout.item_recommended_course);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserCourseViewModel item) {
        ImageUtil.showImage(mContext, helper.getView(R.id.image), item.imageUrl);
        helper.setText(R.id.title, item.title);
        helper.setText(R.id.txt_like_count, String.valueOf(item.likeCount));
        helper.setImageResource(R.id.image_favorite, item.isLike ? R.drawable.icon_heart_red : R.drawable.icon_heart_empty);

        ImageUtil.showImage(mContext, helper.getView(R.id.image_avatar), item.userInfo.avatarUrl);
        helper.setText(R.id.txt_user_name, item.userInfo.userNickname);
    }
}
