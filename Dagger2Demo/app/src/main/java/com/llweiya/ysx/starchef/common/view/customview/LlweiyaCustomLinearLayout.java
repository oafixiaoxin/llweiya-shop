package com.llweiya.ysx.starchef.common.view.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.llweiya.ysx.starchef.common.utils.UIUtil;

import timber.log.Timber;

public class LlweiyaCustomLinearLayout extends LinearLayout {
    private final int defaultRadiusDP = 0;
    private final int defaultRadius = UIUtil.dip2pixel(LlweiyaApp.getAppComponent().getApplication(), defaultRadiusDP);

    private int bottomLeftRadius = defaultRadius;
    private int bottomRightRadius = defaultRadius;
    private int topRightRadius = defaultRadius;
    private int topLeftRadius = defaultRadius;

    private int backgroundColor;
    private int selectedBackgroundColor;
    private int disabledColor;
    private int strokeColor;
    private float strokeWidth;
    private int cornerRadius = 0;

    private float[] rids;
    private float percent;
    private int percentColor;

    private Paint mPaint = new Paint();
    private RectF mRectF = new RectF();
    private RectF backRectF = new RectF();

    public LlweiyaCustomLinearLayout(Context context) {
        this(context, null);
    }

    public LlweiyaCustomLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LlweiyaCustomLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, @Nullable AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.LlweiyaCustomLinearLayout);
        cornerRadius = ta.getDimensionPixelSize(R.styleable.LlweiyaCustomLinearLayout_lwllyRadius, 0);
        backgroundColor = ta.getColor(R.styleable.LlweiyaCustomLinearLayout_lwllyBackgroundColor, getResources().getColor(R.color.transparent));
        selectedBackgroundColor = ta.getColor(R.styleable.LlweiyaCustomLinearLayout_lwllySelectedBackgroundColor, getResources().getColor(R.color.transparent));
        disabledColor = ta.getColor(R.styleable.LlweiyaCustomLinearLayout_lwllyDisabledColor, 0);
        strokeWidth = ta.getDimensionPixelSize(R.styleable.LlweiyaCustomLinearLayout_lwllyStrokeWidth, 0);
        strokeColor = ta.getColor(R.styleable.LlweiyaCustomLinearLayout_lwllyStrokeColor, getResources().getColor(R.color.transparent));
        if (cornerRadius > 0) {
            // 表示四角统一设置
            float customRadius = (float) UIUtil.dip2pixel(context, cornerRadius);
            rids = new float[]{customRadius, customRadius, customRadius, customRadius, customRadius, customRadius, customRadius, customRadius};
        } else {
            bottomLeftRadius = UIUtil.dip2pixel(context, ta.getInt(R.styleable.LlweiyaCustomLinearLayout_lwllyBottomLeftRadius, defaultRadiusDP));
            bottomRightRadius = UIUtil.dip2pixel(context, ta.getInt(R.styleable.LlweiyaCustomLinearLayout_lwllyBottomRightRadius, defaultRadiusDP));
            topRightRadius = UIUtil.dip2pixel(context, ta.getInt(R.styleable.LlweiyaCustomLinearLayout_lwllyTopRightRadius, defaultRadiusDP));
            topLeftRadius = UIUtil.dip2pixel(context, ta.getInt(R.styleable.LlweiyaCustomLinearLayout_lwllyTopLeftRadius, defaultRadiusDP));
            rids = new float[]{(float)topLeftRadius, (float)topLeftRadius, (float)topRightRadius, (float)topRightRadius,
                    (float)bottomRightRadius, (float)bottomRightRadius, (float)bottomLeftRadius, (float)bottomLeftRadius,};
        }
        ta.recycle();

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(strokeColor);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setAntiAlias(true);

        setBackground(DrawableUtil.getSelector(backgroundColor, selectedBackgroundColor, disabledColor, cornerRadius, rids));
        setWillNotDraw(false);
    }

    @SuppressLint("DrawAllocation")
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (strokeWidth > 0) {
            mRectF.left = mRectF.top = 0.5f * strokeWidth;
            mRectF.right = getMeasuredWidth() - 0.5f * strokeWidth;
            mRectF.bottom = getMeasuredHeight() - 0.5f * strokeWidth;
            canvas.drawRoundRect(mRectF, cornerRadius, cornerRadius, mPaint);
        }
        if (percent > 0) {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(this.percentColor);
            backRectF.left = getX();
            backRectF.top = getY();
            backRectF.right = getX() + getWidth() * this.percent;
            backRectF.bottom = getY() + getHeight();
            canvas.drawRect(backRectF, mPaint);
        }
    }

    /**
     * 修改边框颜色
     * @param colorResource  传值：R.color.XXXX
     */
    public void setStrokeColor(int colorResource){
        try {
            strokeColor = colorResource;
            if (null != mPaint) {
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setColor(strokeColor);
                mPaint.setStrokeWidth(strokeWidth);
            }
            invalidate();
        }catch (Exception e){
            Timber.e(e.getLocalizedMessage());
        }
    }

    public void setNormalCornerRadius(int topLeft, int topRight, int bottomLeft, int bottomRight){
        try {
            bottomLeftRadius = UIUtil.dip2pixel(getContext(), bottomLeft);
            bottomRightRadius = UIUtil.dip2pixel(getContext(), bottomRight);
            topRightRadius = UIUtil.dip2pixel(getContext(), topRight);
            topLeftRadius = UIUtil.dip2pixel(getContext(), topLeft);
            rids = new float[]{(float)topLeftRadius, (float)topLeftRadius, (float)topRightRadius, (float)topRightRadius,
                    (float)bottomRightRadius, (float)bottomRightRadius, (float)bottomLeftRadius, (float)bottomLeftRadius,};
            if (null != mPaint) {
                setBackground(DrawableUtil.getSelector(backgroundColor, selectedBackgroundColor, disabledColor, 0, rids));
            }
            invalidate();
        }catch (Exception e){
            Timber.e(e.getLocalizedMessage());
        }
    }

    public void setNormalCommonRadius(int radius) {
        try {
            cornerRadius = UIUtil.dip2pixel(getContext(), radius);
            if (null != mPaint) {
                setBackground(DrawableUtil.getSelector(backgroundColor, selectedBackgroundColor, disabledColor, cornerRadius, rids));
            }
            invalidate();
        }catch (Exception e){
            Timber.e(e.getLocalizedMessage());
        }
    }

    public void setPercentBackgroundColor(double percent, int color) {
        this.percent = (float)percent / 100;
        this.percentColor = color;
        invalidate();
    }

    public void setNormalBackgroundColor(int colorResource){
        try {
            backgroundColor = ContextCompat.getColor(getContext(), colorResource);
            if (null != mPaint) {
                setBackground(DrawableUtil.getSelector(backgroundColor, selectedBackgroundColor, disabledColor, cornerRadius, rids));
            }
            invalidate();
        }catch (Exception e){
            Timber.e(e.getLocalizedMessage());
        }
    }
}
