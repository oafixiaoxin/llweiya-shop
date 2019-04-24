package com.llweiya.ysx.starchef.common.view;

import com.llweiya.ysx.starchef.common.injection.BaseModule;

/**
 * Created by ysx on 2018/1/15.
 */

public interface BaseView {
    void viewFinish();

    void showProgressView(CharSequence text, boolean cancelable);

    void dismissProgressView();

    void showToast(CharSequence text);

    void showLongToast(CharSequence text);

    BaseModule getBaseModule();
}
