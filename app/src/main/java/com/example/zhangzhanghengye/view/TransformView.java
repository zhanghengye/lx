package com.example.zhangzhanghengye.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.zhangzhanghengye.lx.R;

import java.util.ArrayList;
import java.util.List;

public class TransformView extends View {

    private Paint mPaint;
    private ValueAnimator mAnioator;//属性动画对象
    private List<Ball> mBalls = new ArrayList<>();

    public TransformView(Context context) {
        this(context, null);
    }

    public TransformView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TransformView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.huifu);
        //粒子圆的半径
        float d = 8;
        for (int i = 0; i < mBitmap.getWidth(); i++) {
            for (int j = 0; j < mBitmap.getHeight(); j++) {
                Ball ball = new Ball();
                ball.color = mBitmap.getPixel(i, j);//获取到每一个像素点的颜色值
                ball.x = i * d + d / 2;//公式的意义是每个x坐标都是原本的第i个像素点 * 圆心半径 + 圆心半径的一半。得出的结果就是这个x是那个圆的中心x坐标而不是原来像素点的x
                ball.y = j * d + d / 2;
                ball.r = d / 2;//众所周知取整不取余
                ball.vX = (float) (Math.pow(-1, Math.ceil(Math.random() * 1000)) * 20 * Math.random());
                ball.vY = rangInt();

                //加速度
                ball.aX = 0;
                ball.aY = 0.98f;
                mBalls.add(ball);
            }
        }
        mAnioator = ValueAnimator.ofFloat(0, 1);
        mAnioator.setRepeatCount(-1);
        mAnioator.setDuration(2000);
        mAnioator.setInterpolator(new LinearInterpolator());
        mAnioator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                updateBall();
                invalidate();
            }
        });
    }

    private int rangInt() {
        //35
        int max = Math.max(-15, 35);
        //-16
        int min = Math.min(-15, 35) - 1;
        //在0到（max-min）范围内变化，取大于x的最小整数，在随机。
        return (int) (min + Math.ceil(Math.random() * (max - min)));
    }

    private void updateBall() {
        //更新粒子的位置

        for (Ball mBall : mBalls) {
            //原点位置+上原始速度
            mBall.x += mBall.vX;
            mBall.y += mBall.vY;
            //原始速度+=加速度=真实速度
            mBall.vX += mBall.aX;
            mBall.vY += mBall.aY;

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Ball mBall : mBalls) {
            mPaint.setColor(mBall.color);
            canvas.drawCircle(mBall.x, mBall.y, mBall.r, mPaint);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //监听到如果点下
            mAnioator.start();
        }
        return super.onTouchEvent(event);
    }
}
