package com.llweiya.ysx.starchef.business.dependency.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.dependency.model.TestModel;

/**
 * Created by ysx on 2018/10/21.
 */

public class TagAdapter extends BaseQuickAdapter<TestModel, BaseViewHolder> {
    public TagAdapter() {
        super(R.layout.item_tag);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestModel item) {
        helper.setText(R.id.btn_tag, item.name);
    }
}
