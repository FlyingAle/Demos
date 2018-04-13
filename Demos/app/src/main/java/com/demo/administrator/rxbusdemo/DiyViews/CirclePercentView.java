package com.demo.administrator.rxbusdemo.DiyViews;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.demo.administrator.rxbusdemo.R;
import com.demo.administrator.rxbusdemo.Utils.DisplayUtil;

//圆形百分比控件
public class CirclePercentView extends View {

  private int mCircleBackgroundColor;
  private int mPercentTextColor;
  private float mCircleRadius;
  private float mArcWidth;
  private int mArcColor;
  private float mPercentTextSize;

  private Paint mCirclePaint;
  private Paint mArcPaint;
  private Paint mPercentTextPaint;

  private Rect mTextBound;
  private RectF mArcRectF;

  private float mCurPercent = 0.0f;

  private OnClickListener mOnClickListener;

  public CirclePercentView(Context context) {
    this(context,null);
  }

  public CirclePercentView(Context context,
      @Nullable AttributeSet attrs) {
    this(context, attrs,0);
  }

  public CirclePercentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CirclePercentView,defStyleAttr,0);
    mCircleBackgroundColor = typedArray.getColor(R.styleable.CirclePercentView_circleBackgroundColor,0xff00FFFF);
    mArcColor = typedArray.getColor(R.styleable.CirclePercentView_arcColor,0xffFFFFF0);
    mPercentTextColor = typedArray.getColor(R.styleable.CirclePercentView_percentTextColor,0xffffffff);
    mCircleRadius = typedArray.getDimension(R.styleable.CirclePercentView_radius, DisplayUtil.dip2px(100,context));
    mArcWidth = typedArray.getDimension(R.styleable.CirclePercentView_arcWidth,DisplayUtil.dip2px(12,context));
    mPercentTextSize = typedArray.getDimension(R.styleable.CirclePercentView_percentTextSize,DisplayUtil.sp2px(12,context));
    typedArray.recycle();
    mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mCirclePaint.setStyle(Style.FILL);
    mCirclePaint.setColor(mCircleBackgroundColor);
    mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mArcPaint.setStyle(Style.STROKE);
    mArcPaint.setStrokeWidth(mArcWidth);
    mArcPaint.setColor(mArcColor);
    mArcPaint.setStrokeCap(Cap.ROUND); // 两头圆滑
    mPercentTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPercentTextPaint.setStyle(Style.STROKE);
    mPercentTextPaint.setColor(mPercentTextColor);
    mPercentTextPaint.setTextSize(mPercentTextSize);
    mTextBound = new Rect();
    mArcRectF = new RectF();

    setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if(mOnClickListener!= null)
        {
          mOnClickListener.onClick(CirclePercentView.this);
        }
      }
    });
  }

  @Override
  protected void onDraw(Canvas canvas) {
    canvas.drawCircle(getWidth()/2,getHeight()/2,mCircleRadius,mCirclePaint);
    mArcRectF.set(getWidth()/2 - mCircleRadius + mArcWidth/2,getHeight()/2 - mCircleRadius + mArcWidth/2,getWidth()/2+mCircleRadius-mArcWidth/2,getHeight()/2+mCircleRadius-mArcWidth/2);
    canvas.drawArc(mArcRectF,270,360 * mCurPercent/100,false,mArcPaint);
    String percentText = mCurPercent + "%";
    mPercentTextPaint.getTextBounds(percentText,0,String.valueOf(percentText).length(),mTextBound);
    canvas.drawText(percentText,getWidth()/2-mTextBound.width()/2,getHeight()/2-mTextBound.height()/2,mPercentTextPaint);
  }

  @Override
  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    setMeasuredDimension(measureDimension(widthMeasureSpec),measureDimension(heightMeasureSpec));
  }

  public int measureDimension(int measureSpec)
  {
    float result;
    int specMode =  MeasureSpec.getMode(measureSpec);
    int specSize = MeasureSpec.getSize(measureSpec);
    if(specMode == MeasureSpec.EXACTLY)
    {
      result = specSize;
    } else
    {
      result = 2*mCircleRadius;
      if(specMode == MeasureSpec.AT_MOST)
      {
        result = Math.min(result,specSize);
      }
    }
    return (int)result;
  }

  public void setCirclePercentOnClickListener(OnClickListener onClickListener)
  {
    mOnClickListener = onClickListener;
  }

  public void setCurPercent(float curPercent)
  {
    ValueAnimator valueAnimator = ValueAnimator.ofFloat(mCurPercent,curPercent);
    valueAnimator.setDuration((long) Math.abs((mCurPercent - curPercent)*20));
    valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        float value = (float) animation.getAnimatedValue();
        mCurPercent = Math.round(value*10)/10;
        invalidate();
      }
    });
    valueAnimator.start();
  }
}
