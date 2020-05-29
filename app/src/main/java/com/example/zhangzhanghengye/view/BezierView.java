package com.example.zhangzhanghengye.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BezierView extends View {

    private Paint mPaint, mLinePointPaint;
    private Path mPath;
    //多控制点  没有分控制点和数据点
    private List<PointF> mControlPoints;

    public BezierView(Context context) {
        this(context, null);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //绘制贝塞尔曲线的画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);

        //绘制随机点和连线的🖌️
        mLinePointPaint = new Paint();
        mLinePointPaint.setAntiAlias(true);
        mLinePointPaint.setStrokeWidth(4);
        mLinePointPaint.setStyle(Paint.Style.STROKE);
        mLinePointPaint.setColor(Color.GRAY);

        mPath = new Path();
        mControlPoints = new ArrayList<>();
        init();
    }

    //初始化随机的8个点
    private void init() {
        //每次初始化时清空之前的数据
        mControlPoints.clear();
        Random random = new Random();
        for ( int i = 0 ; i < 9 ; i++ ) {
            int x = random.nextInt(300) + 100;
            int y = random.nextInt(500) + 200;
            PointF pointF = new PointF(x, y);
            mControlPoints.add(pointF);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //控制点和控制点连线
        int size = mControlPoints.size();
        PointF mPointf;
        for ( int i = 0 ; i < size ; i++ ) {
            mPointf = mControlPoints.get(i);
            if (i > 0) {
                canvas.drawLine(mControlPoints.get(i - 1).x, mControlPoints.get(i - 1).y, mPointf.x, mPointf.y, mLinePointPaint);
            }
            canvas.drawCircle(mPointf.x, mPointf.y, 10, mLinePointPaint);
        }

        buildBezierPoints();
        canvas.drawPath(mPath, mPaint);
    }

    private void buildBezierPoints() {
        //清理路径
        mPath.reset();

        ArrayList<PointF> pointFS = new ArrayList<>();
        //一共是几阶贝塞尔曲线
        int order = mControlPoints.size() - 1;

        //画的密集 帧为单位
        float delta = 1.0f / 500;

        /*
         *最终在这设置贝塞尔的绘制路径 这个帧代表的是用多少个像素点来绘制出这一条贝塞尔曲线  而这一条贝塞尔曲线又有多少个像素点
         * 像素点越多 曲线的每个弯会更流畅
         */
        for ( float i = 0 ; i <= 1 ; i += delta ) {
            PointF pointF = new PointF(deCastelJauX(order, 0, i), deCastelJauY(order, 0, i));
            pointFS.add(pointF);
            if (pointFS.size() == 1) {
                mPath.moveTo(pointFS.get(0).x, pointFS.get(0).y);
            } else {
                mPath.lineTo(pointF.x, pointF.y);
            }
        }
    }

    /**
     * deCastelJau算法
     * p(i,j) = (1-t)*p(i-1,j)+ t*p(i-1,j+1)
     *
     * @param order 阶数
     * @param i     控制点
     * @param t     时间
     * @return 坐标
     */
    private float deCastelJauY(int order, int i, float t) {
        if (order == 1) {
            /*
             * 需要绘制的线的终点 = （1-当前所在点） * 当前的y点 + 下一个需要到达的y点
             * 因为两个数据点被认为是从0到1之间 所以取1-当前所在点的值*需要到达的值 + 当前所在的值 * 最终到达的下一个值
             * 每绘制一次  都是当前点所在的位置 * 剩余的比例 + 当前所占比较*到下一个点的坐标
             */
            return (1 - t) * mControlPoints.get(i).y + t * mControlPoints.get(i + 1).y;
        }
        return (1 - t) * deCastelJauY(order - 1, i, t) + t * deCastelJauY(order - 1, i + 1, t);
    }

    private float deCastelJauX(int order, int i, float t) {
        if (order == 1) {
            return (1 - t) * mControlPoints.get(i).x + t * mControlPoints.get(i + 1).x;
        }
        return (1 - t) * deCastelJauX(order - 1, i, t) + t * deCastelJauX(order - 1, i + 1, t);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            init();
            invalidate();
        }
        return true;
    }
}
