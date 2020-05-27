package com.llweiya.ysx.starchef.business.community.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.community.model.CommentItemViewModel;
import com.llweiya.ysx.starchef.common.utils.ImageUtil;
import com.llweiya.ysx.starchef.common.utils.TimeFormatUtil;

import java.util.Date;

public class CommentItemAdapter extends BaseQuickAdapter<CommentItemViewModel, BaseViewHolder> {
    public CommentItemAdapter() {
        super(R.layout.item_user_course_comment);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentItemViewModel item) {
        ImageUtil.showImage(mContext, helper.getView(R.id.image_avatar), item.userInfoModel.avatarUrl);
        helper.setText(R.id.txt_user_name, item.userInfoModel.userNickname);
        helper.setText(R.id.txt_create_time, TimeFormatUtil.formatToYYYYHMDDHHMDot(new Date(item.createTime)));
        helper.setText(R.id.txt_content, item.content);
        helper.setText(R.id.txt_like_count, item.likeCount > 0 ? String.valueOf(item.likeCount) : "点赞");
        helper.setImageResource(R.id.image_like, item.isLike ? R.drawable.icon_praise_selected : R.drawable.icon_praise);
        helper.setTextColor(R.id.txt_like_count, mContext.getResources().getColor(item.isLike ? R.color.llweiya_main_color : R.color.llweiya_text_color_black));
    }
}
