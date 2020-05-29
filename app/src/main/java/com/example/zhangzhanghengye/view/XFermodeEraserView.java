package com.example.zhangzhanghengye.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.zhangzhanghengye.lx.R;

public class XFermodeEraserView extends View {

    private Paint mPaint;
    private Bitmap mTxtBmp, mSrcBmp, mDstBmp;
    private Path mPath;

    public XFermodeEraserView(Context context) {
        this(context, null);
    }

    public XFermodeEraserView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XFermodeEraserView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(80);
        //禁止硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint);
        //下层图片
        mTxtBmp = BitmapFactory.decodeResource(getResources(), R.drawable.huifu);
        //上层图片
        mSrcBmp = BitmapFactory.decodeResource(getResources(), R.drawable.back9);

        mDstBmp = Bitmap.createBitmap(mSrcBmp.getWidth(), mSrcBmp.getHeight(), Bitmap.Config.ARGB_8888);
        //路径（贝塞尔曲线）
        mPath = new Path();
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制中奖结果
        canvas.drawBitmap(mTxtBmp, (mSrcBmp.getWidth()-mTxtBmp.getWidth()) / 2, (mSrcBmp.getHeight() - mTxtBmp.getHeight()) / 2, mPaint);
        //使用离屏绘制
        int layerID = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint, Canvas.CLIP_SAVE_FLAG);
        //现将路径绘制到图片上
        Canvas dstCanvas = new Canvas(mDstBmp);
        dstCanvas.drawPath(mPath, mPaint);

        //绘制目标图片
        canvas.drawBitmap(mDstBmp, 0, 0, mPaint);

        //设置图层绘制模式为SRC_OUT ，擦橡皮区域为交际区域需要清理掉像素
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

        //绘制源图像
        canvas.drawBitmap(mSrcBmp, 0, 0, mPaint);

        mPaint.setXfermode(null);

        canvas.restoreToCount(layerID);
    }

    private float mEventX, mEventY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mEventX = event.getX();
                mEventY = event.getY();
                mPath.moveTo(mEventX, mEventY);
                break;
            case MotionEvent.ACTION_MOVE:
                //1-6   6-1=5  a = (手指停止位置 - 手指开始位置) /2 + x1
                float endX = (event.getX() - mEventX) / 2 + mEventX;
                float endY = (event.getY() - mEventY) / 2 + mEventY;
                //画二阶贝塞尔曲线
                mPath.quadTo(mEventX, mEventY, endX, endY);
                mEventX = event.getX();
                mEventY = event.getY();
                break;
        }

        invalidate();
        //返回true消费事件false交给上层处理
        return true;
    }
}
