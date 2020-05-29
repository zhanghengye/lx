package com.example.zhangzhanghengye.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;


public class PathView extends View {

    //路径
    private Path mPath = new Path();
    //画笔
    private Paint mPaint = new Paint();

    public PathView(Context context) {
        super(context);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        //将画笔移动到100，70坐标开始绘制
//        mPath.moveTo(100, 70);
//        //连线 从上一个位置画出一条线 终点为lineTo的参数位置
//        mPath.rLineTo(40,430);
//        //mPath.lineTo(140, 500);
//        mPath.lineTo(300,400);
//       // mPath.close();//闭合状态  如果最终的位置没有和任何一个点链接  则自动连接到初始点
        //添加子图形  addxxxxx
//        mPath.addArc(200,200,400,400,-360,360);
//        //cw代表顺时针 ccw代表逆时针绘制
//        mPath.addRect(200,200,400,400,Path.Direction.CW);

//        mPath.moveTo(0, 0);
//        mPath.lineTo(100, 100);
//        //强制移动 true代表不会从上一个点连接到强制移动到的起点  false 会连接
//        mPath.arcTo(400, 200, 600, 400, 0, 270, true);
        //绘制一个矩形
//        RectF rectF = new RectF(200,400,500,600);
//        //其中的第2、3个参数分表代表了每个角的x和y的坐标
//        mPath.addRoundRect(rectF,10,10,Path.Direction.CW);
        //二阶贝塞尔曲线  是由两个数据点（起始点，和终止点） 和一个控制点组成
//        mPath.moveTo(200,500);
//        //x1 代表的是控制点的x轴坐标 y1代表的是y轴，x2和y2分别代表的是终止点的x，y的坐标
//        mPath.quadTo(300,100,400,500);

        //三阶贝塞尔曲线

//        mPath.moveTo(150, 150);
//        //x1 代表的是控制点1的x轴坐标 y1代表的是y轴，x2和y2分别代表的是控制点2的坐标x，y的坐标,x3和y3分别代表的是终止点的x，y的坐标
//        mPath.cubicTo(350, 50, 500, 500, 600, 150);

        mPath.moveTo(0, canvas.getHeight() / 2);
        mPath.quadTo(canvas.getWidth()/4,canvas.getHeight()/2-150,canvas.getWidth()/2,canvas.getHeight() / 2);

        canvas.drawPath(mPath, mPaint);
    }
}
