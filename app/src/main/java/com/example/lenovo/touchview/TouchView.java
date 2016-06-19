package com.example.lenovo.touchview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lenovo on 2016/6/17.
 */
public class TouchView extends View {
    private Paint paint;
    private Path path;
    private int alpha;
    private Point center;
    private Point start;
    private Point end;
    private int x;
    private int y;
    private int moveX=0;
    private int moveY=0;
    public int startX;
    public int startY;
    private boolean flag=true;
    private  int radius;
    private int color;
    private int speed;

    public TouchView(Context context) {
        this(context, null);
    }

    public TouchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.TouchView, defStyleAttr, 0);
        alpha=array.getDimensionPixelSize(R.styleable.TouchView_alpha,0);
        color=array.getColor(R.styleable.TouchView_viewColor, Color.BLUE);
        radius=array.getDimensionPixelSize(R.styleable.TouchView_yPosition,0);
        speed=array.getInt(R.styleable.TouchView_viewSpeed, 5);
        paint=new Paint();
       // init();
    }

    private void init(){


       // paint.setAlpha(alpha);

    }

    public void setPosition(int startX, int startY){
        this.startX=startX;
        this.startY=startY;
    }

    @Override
    public void onSizeChanged(int x, int y, int oldw, int oldh){

        start=new Point(0, 0);
        end=new Point(getWidth(), 0);
        center=new Point(getWidth()/2, 0);
    }

    @Override
    public void onDraw(Canvas canvas){

        if (alpha<100&&flag){
            alpha++;
        }
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        paint.setAlpha(alpha);
        path=new Path();
        if(flag){
            center.y=radius;
            path.moveTo(start.x, start.y);
            path.quadTo(center.x, center.y, end.x, end.y);
            canvas.drawPath(path, paint);
        }
        else {
            if(radius>0){
              //  Log.e("center",center.x+" "+center.y);
                if(alpha>30){
                    alpha--;
                }
                radius=radius-speed*3;
                center.y=radius;
                path.moveTo(start.x, start.y);
                path.quadTo(center.x, center.y, end.x, end.y);
                canvas.drawPath(path, paint);
            }

        }
      //  Log.e("alpha",paint.getAlpha()+"");
        invalidate();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                alpha=0;
                radius=30;
                flag=true;
                x= (int) event.getX();
                y= (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                moveX=(int) event.getX()-x;
                moveY=(int)event.getY()-y;
                radius=moveY;
                if(moveY>getHeight()){
                    radius=getHeight();
                }
                else if(moveY<0){
                    radius=0;
                }

                break;
            case MotionEvent.ACTION_UP:
                flag=false;
                break;

        }
        return  true;
    }
}
