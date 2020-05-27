package com.llweiya.ysx.starchef.common.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.common.ComponentReflectionInjector;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.llweiya.ysx.starchef.common.injection.AppComponent;
import com.llweiya.ysx.starchef.common.injection.BaseComponent;
import com.llweiya.ysx.starchef.common.injection.BaseModule;
import com.llweiya.ysx.starchef.common.injection.DaggerBaseComponent;
import com.llweiya.ysx.starchef.common.utils.LanguageUtil;
import com.llweiya.ysx.starchef.common.utils.SpUtil;
import com.llweiya.ysx.starchef.common.utils.UIUtil;

import java.util.Locale;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

/**
 * Created by ysx on 2018/1/15.
 */

public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity
        implements BaseView, BGASwipeBackHelper.Delegate {

    protected BGASwipeBackHelper mSwipeBackHelper;

    private ProgressDialog dialog;
    private Toast toast;

    protected B viewBinding;

    private BaseModule baseModule;

    protected boolean needElevation = true;

    @Override
    protected void onCreate(Bundle bundle) {
        initSwipeBackFinish();
        super.onCreate(bundle);

        setupComponent(LlweiyaApp.getAppComponent(), new BaseModule(this, this));
        injectComponent();

        if (this.getLayoutId() > 0) {
            View rootView = getLayoutInflater().inflate(this.getLayoutId(), null, false);
            viewBinding = DataBindingUtil.bind(rootView);
            this.setContentView(rootView);
        }

        setNeedElevation();
        initActionBar();

        onCreateSubView(bundle);

        if (!selfDefineStatus()) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white), true);
            } else {
                StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.llweiya_text_color_gray), true);
            }
        }

    }

    protected abstract void onCreateSubView(Bundle savedInstanceState);

    private void setupComponent(AppComponent appComponent, BaseModule baseModule) {
        this.baseModule = baseModule;

        ComponentReflectionInjector<BaseComponent> componentReflectionInjector = new
                ComponentReflectionInjector<>(BaseComponent.class, DaggerBaseComponent
                .builder()
                .baseModule(baseModule)
                .appComponent(appComponent).build());
        componentReflectionInjector.inject(this);
    }

    protected void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                actionBar.setElevation(needElevation ? 1.0f : 0);
            }
            actionBar.setHomeAsUpIndicator(UIUtil.actionBarBackArrow(this));
        }
    }

    public abstract int getLayoutId();

    public abstract void injectComponent();

    public void setNeedElevation(){

    }

    protected boolean selfDefineStatus() {
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void viewFinish() {
        this.finish();
    }

    @Override
    public synchronized void showProgressView(CharSequence test, boolean cancelable) {
        if (dialog == null) {
            dialog = new ProgressDialog(this, ProgressDialog.THEME_HOLO_LIGHT);
            dialog.setOnDismissListener(d -> onProgressDismiss());
        }
        dialog.setMessage(test);
        if (cancelable) {
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(null);
        } else {
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i == KeyEvent.KEYCODE_SEARCH) {
                        return true;
                    }
                    return false;
                }
            });
        }
        dialog.show();
    }

    @Override
    public void dismissProgressView() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    protected void onProgressDismiss() {

    }

    @Override
    public void showToast(CharSequence text) {
        if (toast == null) {
            toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    @Override
    public void showLongToast(CharSequence text) {
        if (toast == null) {
            toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        } else {
            toast.setText(text);
            toast.setDuration(Toast.LENGTH_LONG);
        }
        toast.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if (mSwipeBackHelper.isSliding()) {
            return;
        }
        mSwipeBackHelper.backward();
        viewFinish();
    }

    @Override
    public BaseModule getBaseModule() {
        return baseModule;
    }

    /**
     * 此方法先于 onCreate()方法执行
     * @param baseContext
     */
    @Override
    protected void attachBaseContext(Context baseContext) {
        //获取我们存储的语言环境 比如 "en","zh",等等
        String locale = Locale.getDefault().getLanguage();
        String language = SpUtil.getInstance(LlweiyaApp.getCurrentActivity()).getString(SpUtil.LANGUAGE);
        super.attachBaseContext(LanguageUtil.attachBaseContext(baseContext, locale));
    }

    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);

        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。

        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
//        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
        // 设置底部导航条是否悬浮在内容上，默认值为 false
        mSwipeBackHelper.setIsNavigationBarOverlap(false);
    }

    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     *
     * @return
     */
    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    /**
     * 正在滑动返回
     *
     * @param slideOffset 从 0 到 1
     */
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {
    }

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    @Override
    public void onSwipeBackLayoutCancel() {
    }

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }
}
