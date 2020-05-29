package com.example.zhangzhanghengye.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class DragBubbleView extends View {
    public DragBubbleView(Context context) {
        this(context, null);
    }

    public DragBubbleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragBubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1、原始状态  一个圆+消息数量

        //2、拖拽状态 在原点还会有一个圆 加上被拖拽的圆和消息数量 加两条贝塞尔曲线

        //3、脱离状态  一个圆+消息数量

        //4、爆炸状态  当松开手时圆距离原始位置一定远之后就会爆炸消失

    }


}
