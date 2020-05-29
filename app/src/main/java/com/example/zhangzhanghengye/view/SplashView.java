package com.example.zhangzhanghengye.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import com.example.zhangzhanghengye.lx.R;


public class SplashView extends View {

    //旋转圆的画笔
    private Paint mPaint;
    //空心圆的画笔
    private Paint mHolePaint;
    //属性动画
    private ValueAnimator mValueAnimator;

    //背景色
    private int mBackgroundColor = Color.WHITE;
    private int[] mCircleColors;

    //表示旋转圆的中心坐标
    private float mCenterX;
    private float mCenterY;
    //表示斜对角线长度的一半，扩散圆最大半径
    private float mDistance;

    //6个小球的半径
    private float mCircleRadius = 8;
    //旋转大圆的半径
    private float mRotateRadius = 50;

    //当前大圆的旋转角度
    private float mCurrentRotateAngle = 0f;
    //当前大圆的半径
    private float mCurrentRotateRadius = mRotateRadius;
    //扩散圆的半径
    private float mCurrentHoleRadius = 0F;
    //表示动画的时长
    private long mRotateDuration = 1200;

    public SplashView(Context context) {
        this(context, null);
    }

    public SplashView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SplashView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        //这个参数表示这个画笔已经设置了抗锯齿效果
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mHolePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHolePaint.setStyle(Paint.Style.STROKE);
        mHolePaint.setColor(mBackgroundColor);

        mCircleColors = context.getResources().getIntArray(R.array.circle_color);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w * 1f / 2;
        mCenterY = h * 1f / 2;
        //这两个值的周长的平方根/2就是mDistance
        mDistance = (float) (Math.hypot(w, h) / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mSplash == null) {
            mSplash = new RotateState();
        }
        mSplash.drawState(canvas);
    }

    private SplashState mSplash;

    private abstract class SplashState {
        abstract void drawState(Canvas canvas);
    }


    //1、6个小球旋转

    private class RotateState extends SplashState {

        private RotateState() {
            mValueAnimator = ValueAnimator.ofFloat(0, (float) (Math.PI * 2));
            mValueAnimator.setRepeatCount(2);
            mValueAnimator.setDuration(mRotateDuration);
            mValueAnimator.setInterpolator(new LinearInterpolator());
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    //当前大圆的旋转角度
                    mCurrentRotateAngle = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            mValueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    //当动画结束时执行,让onDraw方法中的回掉方法进行重新回掉
                    mSplash = new MerginState();
                }
            });
            mValueAnimator.start();
        }

        @Override
        void drawState(Canvas canvas) {
            //绘制背景
            drawBackground(canvas);
            //绘制6个小球
            drawCircles(canvas);
        }
    }

    private void drawCircles(Canvas canvas) {
        //首先获得两个小球之间的角度
        float rotateAngle = (float) (Math.PI * 2 / mCircleColors.length);
        for ( int i = 0 ; i < mCircleColors.length ; i++ ) {
            //其实旋转的是大圆  如果不加上大圆的旋转角度 那么小圆就永远会在固定的位置
            float angle = i * rotateAngle + mCurrentRotateAngle;
            //x= 半径*cos(小球之间的角度)+第一个小球的x坐标
            //y= 半径*sin(小球之间的角度)+第一个小球的y坐标
            //cx = 论点的余弦 * 50 + 要绘制的第一个x轴坐标
            double c = Math.cos(angle);
            double s = Math.sin(angle);
            float cx = (float) (c * mCurrentRotateRadius + mCenterX);
            float cy = (float) (s * mCurrentRotateRadius + mCenterY);
            mPaint.setColor(mCircleColors[i]);
            canvas.drawCircle(cx, cy, mCircleRadius, mPaint);
        }

    }

    private void drawBackground(Canvas canvas) {
        //  表示进入第三种动画
        if (mCurrentHoleRadius > 0) {
            //绘制空心圆
            float strokeWidth = mDistance - mCurrentHoleRadius;
            float radius = strokeWidth / 2 + mCurrentHoleRadius;
            mHolePaint.setStrokeWidth(strokeWidth);
            //让最外部的两个点来做x，y点的初始点
            canvas.drawCircle(mCenterX, mCenterY, radius, mHolePaint);
        } else {
            canvas.drawColor(mBackgroundColor);
        }
    }

    //2、扩散聚合

    private class MerginState extends SplashState {

        private MerginState() {
            //从小圆的半径到大圆的半径进行变化
            mValueAnimator = ValueAnimator.ofFloat(mCircleRadius, mRotateRadius);
            mValueAnimator.setDuration(mRotateDuration);
            //参数表示大圆的扩张力有多大
            mValueAnimator.setInterpolator(new OvershootInterpolator(15f));
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    //当前大圆的半径
                    mCurrentRotateRadius = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            mValueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    //当动画结束时执行,让onDraw方法中的回掉方法进行重新回掉
                    mSplash = new ExpandState();
                }
            });
            mValueAnimator.reverse();
        }

        @Override
        void drawState(Canvas canvas) {
            //绘制背景
            drawBackground(canvas);
            //绘制圆的时候其实是mRotateRadius（大圆的半径）这个参数一直在改变  我们只需要改变这个属性就👌了
            drawCircles(canvas);

        }
    }

    //3、水波纹（也就是空心圆逐渐放大）

    private class ExpandState extends SplashState {

        private ExpandState() {
            //从小圆的半径到大圆的半径进行变化
            mValueAnimator = ValueAnimator.ofFloat(mCircleRadius, mDistance);
            mValueAnimator.setDuration(mRotateDuration);
            //参数表示大圆的扩张力有多大
            mValueAnimator.setInterpolator(new LinearInterpolator());
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    //扩散圆的半径
                    mCurrentHoleRadius = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            mValueAnimator.start();
        }

        @Override
        void drawState(Canvas canvas) {
            drawBackground(canvas);
        }
    }
}
