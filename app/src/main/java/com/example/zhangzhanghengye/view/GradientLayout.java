package com.example.zhangzhanghengye.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class GradientLayout extends View {

    private Paint mPaint;
    private Shader mShader;

    public GradientLayout(Context context) {
        this(context,null);
    }

    public GradientLayout(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public GradientLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setStyle(Paint.Style.FILL);//描边效果
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mShader = new LinearGradient(canvas.getWidth()/2-150,canvas.getHeight()/2-150,canvas.getWidth()/2+150,canvas.getHeight()/2+150,new int[]{Color.RED,Color.BLUE},null,Shader.TileMode.REPEAT);
//        mPaint.setShader(mShader);
//        canvas.drawCircle(canvas.getWidth()/2,canvas.getHeight()/2,150,mPaint);

        mShader = new RadialGradient(250,250,250,new int[]{Color.BLACK,Color.RED},new float[]{0.1f,0.3f},Shader.TileMode.CLAMP);
        mPaint.setShader(mShader);
        canvas.drawCircle(150,150,150,mPaint);
    }
}
