package com.llweiya.ysx.starchef.business.user.view;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.androidadvance.topsnackbar.TSnackbar;
import com.bartoszlipinski.viewpropertyobjectanimator.ViewPropertyObjectAnimator;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.dependency.view.RecordActivity;
import com.llweiya.ysx.starchef.business.user.injection.DaggerUserComponent;
import com.llweiya.ysx.starchef.business.user.model.MediaBean;
import com.llweiya.ysx.starchef.business.user.presenter.LoginPresenter;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.llweiya.ysx.starchef.common.plugin.DebugTrace;
import com.llweiya.ysx.starchef.common.view.BaseActivity;
import com.llweiya.ysx.starchef.common.view.BaseView;
import com.llweiya.ysx.starchef.common.view.GuideView;
import com.llweiya.ysx.starchef.databinding.ActivityLoginBinding;


import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import io.github.xudaojie.qrcodelib.CaptureActivity;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements BaseView {

    @Inject
    LoginPresenter loginPresenter;

    private TimeTicketHandler timeTicketHandler;

    private HashMap<String, List<MediaBean>> allPhotosTemp = new HashMap<>();//所有照片;
    private List<MediaBean> allVideos = new ArrayList<>();
    String[] rateItems = {"完成率", "评价率"};

    private ValueAnimator valueAnimator;
    private ValueAnimator valueAnimator1;
    private ObjectAnimator objectAnimator;
    private AnimatorSet animatorSet;
    private Badge badge;
    private int count = 3;
    private GuideView guideView;

    private final int REQUEST_QR_CODE = 1;

    private static final int REDIRECT_DELAY = 3 * 1000;

    private Boolean isPause = false;

    private TimerTask task = new TimerTask() {
        public void run() {
            Message message = new Message();
            message.what = 1;
            timeTicketHandler.sendMessage(message);
        }

    };

    private Timer timer = new Timer();

    @Override
    public void onCreateSubView(Bundle savedInstanceState) {
        setTitle("test");

        viewBinding.txtTest.setText("yanshuxin");

        timeTicketHandler = new TimeTicketHandler(this);
        timer.schedule(task, REDIRECT_DELAY, REDIRECT_DELAY);

        if (loginPresenter == null) {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        } else {
            loginPresenter.test();
        }

        viewBinding.btnTetst.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, RegisterActivity.class);
            LoginActivity.this.startActivity(intent);

//            CustomToast.makeText(this, "数据加载失败", Toast.LENGTH_LONG).showWarning();

//            if (!valueAnimator1.isRunning()) {
//                valueAnimator1.start();
//                Log.e("yanshuxin", "start");
//            } else {
//                Log.e("yanshuxin", "end");
//            }
//            if (!isPause) {
//                valueAnimator1.pause();
//                isPause = true;
//            } else {
//                valueAnimator1.resume();
//                isPause = false;
//            }
//            count = 0;
//            badge.setBadgeNumber(count);
        });

//        viewBinding.bgaIv.showTextBadge("9");
//        viewBinding.bgaIv.getBadgeViewHelper().setBadgeTextSizeSp(9);
//        viewBinding.bgaIv.getBadgeViewHelper().setBadgePaddingDp(2);

        badge = new QBadgeView(this).bindTarget(viewBinding.btnTetst).setBadgeNumber(count);

//        new QBadgeView(this).bindTarget(viewBinding.btnTetst).setBadgeNumber(5).setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
//            @Override
//            public void onDragStateChanged(int dragState, Badge badge, View targetView) {
//
//            }
//        });

//        TopSnackbar.make(viewBinding.rlayoutRoot, "Hello world", TopSnackbar.LENGTH_LONG).show();

        TSnackbar.make(viewBinding.rlayoutRoot, "hello world", TSnackbar.LENGTH_LONG).show();

//        Snackbar.make(getWindow().getDecorView().getRootView(), "666", Snackbar.LENGTH_SHORT).setAction("go", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        }).show();

//        valueAnimator = makeDeterminateCircularPrimaryProgressAnimator(viewBinding.mpbClose);
//        valueAnimator1 = makeDeterminateCircularPrimaryProgressAnimator1();
//        objectAnimator = makeObjectAnimator();
        animatorSet = makeAnimatorSet();

//        ObjectAnimator layoutAnimator = ViewPropertyObjectAnimator
//                .animate((QBadgeView)badge)
//                .get();
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(layoutAnimator);
//        animatorSet.setDuration(1000);
//        animatorSet.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                Log.e("yanshuxin", "start");
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                Log.e("yanshuxin", "end");
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                Log.e("yanshuxin", "cancel");
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//                Log.e("yanshuxin", "repeat");
//            }
//        });
//        animatorSet.start();

//        final Dialog dialog = new Dialog(this, R.style.custom_dialog);
//        TextView textView = new TextView(this);
//        textView.setText("click");
//        textView.setTextSize(18);
//        textView.setTextColor(Color.WHITE);
//        textView.setGravity(Gravity.CENTER);
//        textView.setPadding(0, 0, 0, 600);
//
//        View view = LayoutInflater.from(this).inflate(R.layout.bg_layer, null);
//        view.findViewById(R.id.btn_layer).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (dialog.isShowing()) {
//                    dialog.dismiss();
//                }
//            }
//        });
//
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        dialog.setContentView(view, layoutParams);
//        dialog.show();
//        WindowManager windowManager = getWindowManager();
//        Display display = windowManager.getDefaultDisplay();
//        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
//        lp.width = (int) (display.getWidth()); // 设置宽度
//        lp.height = (int) (display.getHeight());
//        dialog.getWindow().setAttributes(lp);

        viewBinding.buttonClock.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, CaptureActivity.class);
            LoginActivity.this.startActivityForResult(intent, REQUEST_QR_CODE);
//            RxActivityTool.skipActivityForResult(this, ActivityScanerCode.class, REQUEST_QR_CODE);
        });

        viewBinding.btnRetyclerView.setOnClickListener(v -> {
            //卡片层叠
            Intent intent = new Intent();
//            intent.setClass(LoginActivity.this, CardStackActivity.class);
            intent.setClass(LoginActivity.this, UserInfoActivity.class);
            LoginActivity.this.startActivity(intent);
        });

        viewBinding.btnRecord.setOnClickListener(v -> {
            //录音
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, RecordActivity.class);
            LoginActivity.this.startActivity(intent);
        });

        viewBinding.btnChangeNightMode.setOnClickListener(v -> {
            int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            if (mode == Configuration.UI_MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else if (mode == Configuration.UI_MODE_NIGHT_NO) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {

            }
            recreate();
        });

        viewBinding.btnOpenVideoCollection.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 6001);
            } else {
                getAllVideoInfos();
            }
        });

        viewBinding.txtSkip.setOnClickListener(v -> {

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 6001:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getAllVideoInfos();
                } else {
                    showToast("获取相册权限失败");
                }
                break;
            default:
                break;
        }
    }

    /**
     * 获取手机中所有视频的信息
     */
    private void getAllVideoInfos(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                //更新界面
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //...
//                    }
//                });
//            }
//        }).start();

        Uri mImageUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] proj = { MediaStore.Video.Thumbnails._ID
                , MediaStore.Video.Thumbnails.DATA
                ,MediaStore.Video.Media.DURATION
                ,MediaStore.Video.Media.SIZE
                ,MediaStore.Video.Media.DISPLAY_NAME
                ,MediaStore.Video.Media.DATE_MODIFIED};
        Cursor mCursor = getContentResolver().query(mImageUri,
                proj,
                MediaStore.Video.Media.MIME_TYPE + "=?",
                new String[]{"video/mp4"},
                MediaStore.Video.Media.DATE_MODIFIED+" desc");
        if(mCursor!=null){
            while (mCursor.moveToNext()) {
                // 获取视频的路径
                int videoId = mCursor.getInt(mCursor.getColumnIndex(MediaStore.Video.Media._ID));
                String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Video.Media.DATA));
                int duration = mCursor.getInt(mCursor.getColumnIndex(MediaStore.Video.Media.DURATION));
                long size = mCursor.getLong(mCursor.getColumnIndex(MediaStore.Video.Media.SIZE))/1024; //单位kb
                if(size<0){
                    //某些设备获取size<0，直接计算
                    Log.e("dml","this video size < 0 " + path);
                    size = new File(path).length()/1024;
                }
                String displayName = mCursor.getString(mCursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
                long modifyTime = mCursor.getLong(mCursor.getColumnIndex(MediaStore.Video.Media.DATE_MODIFIED));//暂未用到

                //提前生成缩略图，再获取：http://stackoverflow.com/questions/27903264/how-to-get-the-video-thumbnail-path-and-not-the-bitmap
                MediaStore.Video.Thumbnails.getThumbnail(getContentResolver(), videoId, MediaStore.Video.Thumbnails.MICRO_KIND, null);
                String[] projection = { MediaStore.Video.Thumbnails._ID, MediaStore.Video.Thumbnails.DATA};
                Cursor cursor = getContentResolver().query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI
                        , projection
                        , MediaStore.Video.Thumbnails.VIDEO_ID + "=?"
                        , new String[]{videoId+""}
                        , null);
                String thumbPath = "";
                while (cursor.moveToNext()){
                    thumbPath = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA));
                }
                cursor.close();
                // 获取该视频的父路径名
                String dirPath = new File(path).getParentFile().getAbsolutePath();
                //存储对应关系
//                        if (allPhotosTemp.containsKey(dirPath)) {
//                            List<MediaBean> data = allPhotosTemp.get(dirPath);
//                            data.add(new MediaBean(dirPath, path, thumbPath, duration, size, displayName));
//                            continue;
//                        } else {
//                            List<MediaBean> data = new ArrayList<>();
//                            data.add(new MediaBean(dirPath, path, thumbPath, duration, size, displayName));
//                            allPhotosTemp.put(dirPath,data);
//                        }
                allVideos.add(new MediaBean(dirPath, path, thumbPath, duration, size, displayName));
            }
            mCursor.close();
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        testAnnotatedMethod();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void injectComponent() {
        DaggerUserComponent.builder()
                .baseModule(getBaseModule())
                .appComponent(LlweiyaApp.getAppComponent())
                .build()
                .inject(this);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
//        valueAnimator.start();
        if (count > 0) {
//            valueAnimator1.start();
//            objectAnimator.start();
            animatorSet.start();
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        valueAnimator.end();
//        valueAnimator1.end();
//        objectAnimator.end();
        animatorSet.start();
        clearTimer();
    }

    @DebugTrace
    private void testAnnotatedMethod() {
        try{
            Thread.sleep(10);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ValueAnimator makeDeterminateCircularPrimaryProgressAnimator(final ProgressBar progressBar) {
        ValueAnimator animator = ValueAnimator.ofInt(0, 150);
        animator.setDuration(REDIRECT_DELAY);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(
                new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        int value = (int) animator.getAnimatedValue();
                        progressBar.setProgress(value);
                        badge.setBadgePadding(value, true);
                    }
                });
        return animator;
    }

    public ValueAnimator makeDeterminateCircularPrimaryProgressAnimator1() {
        double[] paddings = createPaddings();
        ValueAnimator animator = ValueAnimator.ofInt(0, paddings.length-1);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(0);
        animator.addUpdateListener(
                new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        int value = (int) animator.getAnimatedValue();
//                        viewBinding.bgaIv.getBadgeViewHelper().setBadgePaddingDp((int)paddings[value]);
                    }
                });
        return animator;
    }

    private ObjectAnimator makeObjectAnimator() {
        float currentY = viewBinding.btnTetst.getTranslationY();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat((QBadgeView)badge, "translationY", currentY, currentY-20, currentY+20, currentY);
        objectAnimator.setDuration(1000);
        objectAnimator.setRepeatCount(0);
        return objectAnimator;
    }

    private AnimatorSet makeAnimatorSet() {
        ObjectAnimator layoutAnimator = ViewPropertyObjectAnimator
                .animate((QBadgeView)badge)
                .translationXBy(20)
                .get();
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(layoutAnimator);
        animatorSet.setDuration(1000);
        return animatorSet;
    }

    static class TimeTicketHandler extends Handler {
        private final WeakReference<LoginActivity> launchView;

        TimeTicketHandler(LoginActivity view) {
            launchView = new WeakReference<>(view);
        }
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what) {
                case 1:
                    LoginActivity view = launchView.get();
                    if (view != null) {
                        if (view.count > 0) {
                            view.startAnimation();
                        }
                    }
                    break;
            }
        }
    }

    private void clearTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void startAnimation() {
//        if (!valueAnimator1.isRunning()) {
//            valueAnimator1.start();
//        }
//        if (!objectAnimator.isRunning()) {
//            objectAnimator.start();
//        }
        if (!animatorSet.isRunning()) {
            animatorSet.start();
        }
    }

//    private void pauseAnimation() {
//        valueAnimator1.pause();
//    }

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private double[] createPaddings() {
        int capacity = 10;
        double firstValue = 2.0;
        double rate = 0.2;
        double[] paddings = new double[capacity+2];
        paddings[0] = firstValue;
        for (int i = 1; i <= capacity; i++) {
            paddings[i] = firstValue + rate * i;
        }
        paddings[11] = firstValue;
        return paddings;
    }

    private void setGuideView() {
        // 使用图片
        final ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.img_new_task_guide);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(150, 100);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setLayoutParams(params);

        guideView = GuideView.Builder
                .newInstance(this)
                .setTargetView(viewBinding.btnTetst)//设置目标
                .setCustomGuideView(iv)
                .setDirction(GuideView.Direction.RIGHT_BOTTOM)
                .setShape(GuideView.MyShape.ELLIPSE)   // 设置圆形显示区域，
                .setBgColor(getResources().getColor(R.color.shadow))
                .setOnclickListener(new GuideView.OnClickCallback() {
                    @Override
                    public void onClickedGuideView() {

                    }
                })
                .build();
        guideView.show();
    }

}
