package com.llweiya.ysx.starchef.business.dependency.view.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.dependency.model.ITest;
import com.llweiya.ysx.starchef.business.dependency.model.ListItemViewModel;
import com.llweiya.ysx.starchef.business.dependency.model.TagItemViewModel;
import com.llweiya.ysx.starchef.business.dependency.model.TestModel;

import java.util.List;

/**
 * Created by ysx on 2018/10/20.
 */

public class TestAdapter extends BaseMultiItemQuickAdapter<ITest, BaseViewHolder> {

    private OnCheckMoreListener listener;

    public TestAdapter(List<ITest> list) {
        super(list);
        addItemType(ITest.TYPE_TAG, R.layout.item_grid_view);
        addItemType(ITest.TYPE_LIST, R.layout.item_test);
    }

    @Override
    protected void convert(BaseViewHolder helper, ITest item) {
        switch (item.getItemType()) {
            case ITest.TYPE_TAG:
                setItemGridView(helper, item);
                break;
            case ITest.TYPE_LIST:
                setItemListView(helper, item);
                break;
            default:
                break;
        }
    }

    private void setItemGridView(BaseViewHolder helper, ITest item) {
        TagItemViewModel tagItemViewModel = (TagItemViewModel)item;

        RecyclerView recyclerView = helper.getView(R.id.recycler_tag);

        TagAdapter tagAdapter = new TagAdapter();
        tagAdapter.setNewData(tagItemViewModel.getContent());

        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        recyclerView.setAdapter(tagAdapter);

        tagAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e("tag", tagAdapter.getData().get(position).name);
            }
        });
    }

    private void setItemListView(BaseViewHolder helper, ITest item) {
        ListItemViewModel listItemViewModel = (ListItemViewModel)item;

        RecyclerView recyclerView = helper.getView(R.id.recycler_view);

        ListAdapter listAdapter = new ListAdapter();
        listAdapter.setNewData(listItemViewModel.getContent());

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(listAdapter);

        helper.getView(R.id.txt_check).setOnClickListener(v -> {
            Log.e("list", "check");
            listener.onCheckMore(listAdapter);
        });
    }

    public void updateData(ListItemViewModel model, ListAdapter listAdapter) {
        int pos = listAdapter.getData().size();
        listAdapter.getData().addAll(model.getContent());
        listAdapter.notifyItemInserted(pos);
    }

    public interface OnCheckMoreListener {
        void onCheckMore(ListAdapter adapter);
    }

    public void setOnCheckMoreListener(OnCheckMoreListener listener) {
        this.listener = listener;
    }

}
