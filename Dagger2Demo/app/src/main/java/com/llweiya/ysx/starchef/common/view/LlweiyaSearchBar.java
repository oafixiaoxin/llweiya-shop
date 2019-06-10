package com.llweiya.ysx.starchef.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.llweiya.ysx.starchef.R;

public class LlweiyaSearchBar extends LinearLayout {

    private Context mContext;

    private TextView textView;

    public LlweiyaSearchBar(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public LlweiyaSearchBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public LlweiyaSearchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        inflate(mContext, R.layout.llweiya_search_bar, this);
        textView = findViewById(R.id.text);
    }

    public void setSearchBarHint(String hint) {
        textView.setText(hint);
    }
}
