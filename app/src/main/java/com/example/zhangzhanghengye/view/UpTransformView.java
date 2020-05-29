package com.example.zhangzhanghengye.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.zhangzhanghengye.lx.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UpTransformView extends View {

    private Paint mPaint;
    private List<Ball> mBalls = new ArrayList<>();
    private ValueAnimator mAnimator;

    public UpTransformView(Context context) {
        this(context, null);
    }

    public UpTransformView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UpTransformView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        //设置无边距
        mPaint.setAntiAlias(true);
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.huifu);
        Random random = new Random();
        float d = 5;//原点的半径
        //遍历有多少行的像素点 对每个像素点进行重绘
        for (int i = 0; i < mBitmap.getWidth(); i++) {
            //遍历每行有多少列的像素点 对每个像素点进行重绘
            for (int j = 0; j < mBitmap.getHeight(); j++) {
                Ball ball = new Ball();
                //获取每个像素点的颜色
                ball.color = mBitmap.getPixel(i, j);
                //每个像素点需要画圆的中心点
                ball.x = i * d + d / 2;
                ball.y = j * d + d / 2;
                ball.r = d / 2;
                //初始速度
                ball.vX = 0;
                ball.vY = random.nextInt(6 - 2) + 2;
                //加速度
                ball.aX = 0;
                ball.aY = 0.9f;
                mBalls.add(ball);
            }
        }
        mAnimator = ValueAnimator.ofFloat(0, 1);
        mAnimator.setRepeatCount(-1);
        mAnimator.setDuration(2000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                updateBall();
                invalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //如果想让每个像素点上漂到一定程度重新还原会图片 需要判断每个y轴坐标是否到达指定位置 如果到达这个点就不需要在移动 直到所有的点移动完成
        for (Ball mBall : mBalls) {
            mPaint.setColor(mBall.color);
            canvas.drawCircle(mBall.x, mBall.y, mBall.r, mPaint);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mAnimator.start();
        }
        return super.onTouchEvent(event);
    }

    private void updateBall() {
        for (Ball mBall : mBalls) {
            mBall.x -= mBall.vX;
            mBall.y -= mBall.vY;
            mBall.vX += mBall.aX;
            mBall.vY += mBall.aY;
        }
    }
}
