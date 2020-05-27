package com.llweiya.ysx.starchef.business.community.view;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.annotation.apt.LlweiyaRouter;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.aop.RouterConfig;
import com.llweiya.ysx.starchef.business.community.injection.DaggerCommunityComponent;
import com.llweiya.ysx.starchef.business.community.model.UserDefinedCourseViewModel;
import com.llweiya.ysx.starchef.business.community.presenter.UserDefinedCoursePresenter;
import com.llweiya.ysx.starchef.business.community.view.adapter.CommentItemAdapter;
import com.llweiya.ysx.starchef.business.community.view.adapter.CourseMenuItemAdapter;
import com.llweiya.ysx.starchef.business.community.view.adapter.CourseTagItemAdapter;
import com.llweiya.ysx.starchef.business.community.view.adapter.RecommendedCourseItemAdapter;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.llweiya.ysx.starchef.common.utils.ImageUtil;
import com.llweiya.ysx.starchef.common.utils.TimeFormatUtil;
import com.llweiya.ysx.starchef.common.utils.UIUtil;
import com.llweiya.ysx.starchef.common.utils.Utility;
import com.llweiya.ysx.starchef.common.view.BaseActivity;
import com.llweiya.ysx.starchef.common.view.LiweiyaItemDecoration;
import com.llweiya.ysx.starchef.common.view.LlweiyaGridItemDecoration;
import com.llweiya.ysx.starchef.databinding.ActivityUserDefinedCourseBinding;
import com.lpmas.apt.LWRouter;

import java.util.Date;

import javax.inject.Inject;

/**
 * 用户自制饮食教程详情页面
 */
@LlweiyaRouter(RouterConfig.USERDEFINEDCOURSE)
public class UserDefinedCourseActivity extends BaseActivity<ActivityUserDefinedCourseBinding> implements UserDefinedCourseView {

    @Inject
    UserDefinedCoursePresenter presenter;

    private boolean isUserSubscribed = false;
    private UserDefinedCourseViewModel mViewModel;

    private CourseMenuItemAdapter courseMenuItemAdapter;
    private CourseTagItemAdapter courseTagItemAdapter;
    private CommentItemAdapter commentItemAdapter;
    private RecommendedCourseItemAdapter userRelevantAdapter;

    @Override
    protected void onCreateSubView(Bundle savedInstanceState) {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int option = window.getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            window.getDecorView().setSystemUiVisibility(option);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        configToolbar();
        initAdapter();
        configViewClickListener();

        presenter.loadData(0);
    }

    @Override
    protected boolean selfDefineStatus() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_defined_course;
    }

    @Override
    public void injectComponent() {
        DaggerCommunityComponent.builder()
                .baseModule(getBaseModule())
                .appComponent(LlweiyaApp.getAppComponent())
                .build()
                .inject(this);
    }

    private void configViewClickListener() {
        viewBinding.btnSubscribe.setOnClickListener(v -> {
            isUserSubscribed = !isUserSubscribed;
            viewBinding.btnSubscribe.setSelected(isUserSubscribed);
            viewBinding.btnSubscribe.setText(isUserSubscribed ? "已关注" : "关注");
            viewBinding.btnSubscribe.setTextColor(getResources().getColor(isUserSubscribed ? R.color.llweiya_main_color : R.color.llweiya_text_color_black));
        });
    }

    private void configToolbar() {
        int statusHeight = UIUtil.getStatusBarHeight(this);
        viewBinding.viewStatus.setAlpha(0);
        RelativeLayout.LayoutParams viewStatusParams = (RelativeLayout.LayoutParams)viewBinding.viewStatus.getLayoutParams();
        viewStatusParams.height = statusHeight;

        FrameLayout.LayoutParams toolbarParams = (FrameLayout.LayoutParams)viewBinding.toolbar.getLayoutParams();
        toolbarParams.topMargin = statusHeight;

        setSupportActionBar(viewBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.icon_back_white));
        getSupportActionBar().setTitle("");
        if (Build.VERSION.SDK_INT >= 21) {
            getSupportActionBar().setElevation(1.0f);
        }
        viewBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        viewBinding.imageOperation.setOnClickListener(v -> {
            showToast("imageOperation");
        });
    }

    private void initAdapter() {
        courseMenuItemAdapter = new CourseMenuItemAdapter();
        viewBinding.recyclerMenu.setLayoutManager(new LinearLayoutManager(this));
        viewBinding.recyclerMenu.setAdapter(courseMenuItemAdapter);

        courseTagItemAdapter = new CourseTagItemAdapter();
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        viewBinding.recyclerViewTag.setLayoutManager(layoutManager);
        viewBinding.recyclerViewTag.setAdapter(courseTagItemAdapter);

        commentItemAdapter = new CommentItemAdapter();
        viewBinding.recyclerViewComment.setLayoutManager(new LinearLayoutManager(this));
        viewBinding.recyclerViewComment.setAdapter(commentItemAdapter);
        viewBinding.recyclerViewComment.addItemDecoration(new LiweiyaItemDecoration.Builder()
                .setContext(this)
                .setColor(getResources().getColor(R.color.llweiya_divider))
                .setOrientation(LinearLayoutManager.VERTICAL)
                .setDeviderSpace(UIUtil.dip2pixel(this, 1))
                .build());

        userRelevantAdapter = new RecommendedCourseItemAdapter();
        viewBinding.recyclerViewUserRelevant.setLayoutManager(new GridLayoutManager(this, 2));
        viewBinding.recyclerViewUserRelevant.addItemDecoration(new LlweiyaGridItemDecoration(2, UIUtil.dip2pixel(this, 10), 0));
        viewBinding.recyclerViewUserRelevant.setAdapter(userRelevantAdapter);
        viewBinding.recyclerViewUserRelevant.setItemAnimator(null);
        userRelevantAdapter.setOnItemClickListener((adapter, view, position) -> {
            LWRouter.go(this, RouterConfig.USERDEFINEDCOURSE);
        });
    }

    private void configCourseInfo() {
        viewBinding.txtContent.setText(this.mViewModel.content);
        viewBinding.txtUpdateTime.setText("更新于 " + TimeFormatUtil.formatToYYYYHMDD(new Date(this.mViewModel.lastUpdateTime)));
        ImageUtil.showImage(this, viewBinding.imageCourse, this.mViewModel.courseImage);
        viewBinding.llayoutCommentCheckMore.setVisibility(this.mViewModel.commentCount > 3 ? View.VISIBLE : View.GONE);
        if (this.mViewModel.commentCount > 3) {
            viewBinding.txtCommentCount.setText("查看所有（" + this.mViewModel.commentCount + "）");
        }
        if (Utility.listHasElement(this.mViewModel.menuList)) {
            courseMenuItemAdapter.setNewData(this.mViewModel.menuList);
        }
        if (Utility.listHasElement(this.mViewModel.tagList)) {
            courseTagItemAdapter.setNewData(this.mViewModel.tagList);
        }
        if (Utility.listHasElement(this.mViewModel.commentList)) {
            commentItemAdapter.setNewData(this.mViewModel.commentList.size() > 3 ? this.mViewModel.commentList.subList(0, 3) : this.mViewModel.commentList);
        }
        if (Utility.listHasElement(this.mViewModel.userRelevant)) {
            userRelevantAdapter.setNewData(this.mViewModel.userRelevant);
            viewBinding.llayoutUserRelevant.setVisibility(View.VISIBLE);
        } else {
            viewBinding.llayoutUserRelevant.setVisibility(View.GONE);
        }
    }

    @Override
    public void loadDataSuccess(UserDefinedCourseViewModel viewModel) {
        this.mViewModel = viewModel;
        configCourseInfo();
    }

    @Override
    public void loadFailed(String errorString) {
        showToast(errorString);
    }
}
