package com.llweiya.ysx.starchef.common.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.llweiya.ysx.starchef.common.utils.UIUtil;

import timber.log.Timber;

public class LlweiyaCustomButton extends AppCompatButton {
    private final int defaultRadiusDP = 0;
    private final int defaultRadius = UIUtil.dip2pixel(LlweiyaApp.getAppComponent().getApplication(), defaultRadiusDP);

    private int bottomLeftRadius = defaultRadius;
    private int bottomRightRadius = defaultRadius;
    private int topRightRadius = defaultRadius;
    private int topLeftRadius = defaultRadius;

    private int backgroundColor;
    private int selectedBackgroundColor;
    private int disabledBackgroundColor;
    private int strokeColor;
    private float strokeWidth;
    private int cornerRadius;

    private float[] rids;

    private Paint mPaint = new Paint();
    private RectF mRectF = new RectF();

    public LlweiyaCustomButton(Context context) {
        this(context, null);
    }

    public LlweiyaCustomButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LlweiyaCustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.LlweiyaCustomButton);
        cornerRadius = ta.getDimensionPixelSize(R.styleable.LlweiyaCustomButton_llbtnRadius, 0);
        backgroundColor = ta.getColor(R.styleable.LlweiyaCustomButton_llbtnBackgroundColor, getResources().getColor(R.color.transparent));
        selectedBackgroundColor = ta.getColor(R.styleable.LlweiyaCustomButton_llbtnSelectedBackgroundColor, getResources().getColor(R.color.transparent));
        disabledBackgroundColor = ta.getColor(R.styleable.LlweiyaCustomButton_llbtnDisabledBackgroundColor, getResources().getColor(R.color.transparent));
        strokeWidth = ta.getDimensionPixelSize(R.styleable.LlweiyaCustomButton_llbtnStrokeWidth, 0);
        strokeColor = ta.getColor(R.styleable.LlweiyaCustomButton_llbtnStrokeColor, getResources().getColor(R.color.transparent));
        if (cornerRadius > 0) {
            // 表示四角统一设置
            float customRadius = (float) UIUtil.dip2pixel(context, cornerRadius);
            rids = new float[]{customRadius, customRadius, customRadius, customRadius, customRadius, customRadius, customRadius, customRadius};
        } else {
            bottomLeftRadius = UIUtil.dip2pixel(context, ta.getInt(R.styleable.LlweiyaCustomButton_llbtnBottomLeftRadius, defaultRadiusDP));
            bottomRightRadius = UIUtil.dip2pixel(context, ta.getInt(R.styleable.LlweiyaCustomButton_llbtnBottomRightRadius, defaultRadiusDP));
            topRightRadius = UIUtil.dip2pixel(context, ta.getInt(R.styleable.LlweiyaCustomButton_llbtnTopRightRadius, defaultRadiusDP));
            topLeftRadius = UIUtil.dip2pixel(context, ta.getInt(R.styleable.LlweiyaCustomButton_lltnTopLeftRadius, defaultRadiusDP));
            rids = new float[]{(float)topLeftRadius, (float)topLeftRadius, (float)topRightRadius, (float)topRightRadius,
                    (float)bottomRightRadius, (float)bottomRightRadius, (float)bottomLeftRadius, (float)bottomLeftRadius,};
        }
        ta.recycle();

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(strokeColor);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setAntiAlias(true);

        setGravity(Gravity.CENTER);

        setBackground(DrawableUtil.getSelector(backgroundColor, selectedBackgroundColor, disabledBackgroundColor, cornerRadius, rids));
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (strokeWidth > 0) {
            mRectF.left = mRectF.top = 0.5f * strokeWidth;
            mRectF.right = getMeasuredWidth() - 0.5f * strokeWidth;
            mRectF.bottom = getMeasuredHeight() - 0.5f * strokeWidth;
            canvas.drawRoundRect(mRectF, cornerRadius, cornerRadius, mPaint);
        }
    }

    /**
     * 修改边框颜色
     * @param colorResource  传值：R.color.XXXX
     */
    public void setStrokeColor(int colorResource){
        try {
            strokeColor = ContextCompat.getColor(getContext(), colorResource);
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

    public void setNormalBackgroundColor(int colorResource){
        try {
            backgroundColor = ContextCompat.getColor(getContext(), colorResource);
            if (null != mPaint) {
                setBackground(DrawableUtil.getSelector(backgroundColor, selectedBackgroundColor, disabledBackgroundColor, cornerRadius, rids));
            }
            invalidate();
        }catch (Exception e){
            Timber.e(e.getLocalizedMessage());
        }
    }
}
