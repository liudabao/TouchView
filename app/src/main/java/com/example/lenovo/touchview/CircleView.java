package com.example.lenovo.touchview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/6/19.
 */
public class CircleView extends View {
    private int color;
    private int radius;
    private int speed;
    private int alpha=100;
    private Paint paint;
    private int x=0;
    private int y=0;
    public CircleView(Context context) {
        //super(context);
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        //super(context, attrs);
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.CustomCircle, defStyleAttr, 0);

        color=array.getColor(R.styleable.CustomCircle_CircleColor, Color.GREEN);
        radius=array.getDimensionPixelSize(R.styleable.CustomCircle_CircleRadius,500);
        speed=array.getInt(R.styleable.CustomCircle_Speed, 5);
        paint=new Paint();
      //  this.setOnClickListener(new OnClickListener() {
      //      @Override
       //     public void onClick(View v) {
       //         if(radius==200&&alpha==0){
      //              radius=0;
       //             alpha=100;
      //          }
      //      }
       // });

    }

    @Override
    protected  void onDraw(Canvas canvas){
        //Rect rect=new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight());
        //paint.setColor(Color.YELLOW);
       // canvas.drawRect(rect,paint);

        paint.setColor(color);
        paint.setAlpha(alpha);
        if(radius>0){
            radius=radius-speed;
        }
        else{
            if(alpha>0){
                alpha=alpha-speed;
            }

        }
        canvas.drawCircle(x, y, radius, paint);
        Path path=new Path();
        path.moveTo(0, 0);
        path.quadTo(getWidth()/2, radius, getWidth(), 0);
        canvas.drawPath(path, paint);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x= (int) event.getX();
                y= (int) event.getY();
                if(radius==0&&alpha==0){
                    radius=500;
                    alpha=100;
                }
                break;


        }
        return  false;
    }

}