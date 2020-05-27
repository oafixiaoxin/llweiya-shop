package com.llweiya.ysx.starchef.business.community.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.community.model.MenuItemViewModel;

public class CourseMenuItemAdapter extends BaseQuickAdapter<MenuItemViewModel, BaseViewHolder> {
    public CourseMenuItemAdapter() {
        super(R.layout.item_course_menu);
    }

    @Override
    protected void convert(BaseViewHolder helper, MenuItemViewModel item) {
        helper.setText(R.id.txt_material, item.name);
        helper.setText(R.id.txt_property, item.property);
    }
}
