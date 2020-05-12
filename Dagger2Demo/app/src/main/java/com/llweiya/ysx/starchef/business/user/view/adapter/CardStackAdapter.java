package com.llweiya.ysx.starchef.business.user.view.adapter;

import androidx.cardview.widget.CardView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.user.model.CardStackItemViewModel;

/**
 * Created by ysx on 2018/8/28.
 */

public class CardStackAdapter extends BaseQuickAdapter<CardStackItemViewModel, BaseViewHolder> {

    public CardStackAdapter() {
        super(R.layout.item_card_stack);
    }

    @Override
    protected void convert(BaseViewHolder helper, CardStackItemViewModel item) {
        helper.setText(R.id.txt_name, item.name);
        helper.setText(R.id.txt_subtitle, item.subtitle);
        helper.setText(R.id.txt_description, item.description);
        helper.setImageDrawable(R.id.image, item.picture);

        CardView cardView = helper.getView(R.id.cardView);
        cardView.setCardBackgroundColor(item.backgroundColor);

    }
}
