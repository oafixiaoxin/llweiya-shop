package com.llweiya.ysx.starchef.business.community.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.community.model.ICommunitySectionType;

public class CommunityMainSectionAdapter extends BaseQuickAdapter<ICommunitySectionType, BaseViewHolder> {
    public CommunityMainSectionAdapter() {
        super(R.layout.item_community_main_section);
    }

    @Override
    protected void convert(BaseViewHolder helper, ICommunitySectionType item) {

    }
}
