package com.example.lenovo.touchview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
<<<<<<< HEAD
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
=======
>>>>>>> 9d377006e517cfae9af4d17494fa392c538bcf72
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
    private int alpha=10;
    private Point center;
    private Point start;
    private Point end;
    private int x;
    private int y;
    private int moveX=0;
    private int moveY=0;
<<<<<<< HEAD
    public int startX;
    public int startY;
    private boolean flag=true;
=======
>>>>>>> 9d377006e517cfae9af4d17494fa392c538bcf72

    public TouchView(Context context) {
        this(context, null);
    }

    public TouchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint=new Paint();
        path=new Path();
        paint.setStyle(Paint.Style.FILL);

        paint.setColor(Color.BLUE);
<<<<<<< HEAD
       // paint.setAlpha(alpha);

    }

    public void setPosition(int startX, int startY){
        this.startX=startX;
        this.startY=startY;
    }


    @Override
    public void onSizeChanged(int x, int y, int oldw, int oldh){

        start=new Point(startX, startY);
        end=new Point(startX+getWidth(), startY);
        center=new Point(getWidth()/2, 0);
=======
        paint.setAlpha(alpha);

    }

    @Override
    public void onSizeChanged(int x, int y, int oldw, int oldh){

        start=new Point(0, 0);
        end=new Point(getWidth(), 0);
        center=new Point(getWidth()/2, getHeight()/10);
>>>>>>> 9d377006e517cfae9af4d17494fa392c538bcf72
    }

    @Override
    public void onDraw(Canvas canvas){

<<<<<<< HEAD
        paint.setAlpha(alpha);
=======
        if(alpha<80){
            alpha=alpha+5;
            paint.setAlpha(alpha);
        }
>>>>>>> 9d377006e517cfae9af4d17494fa392c538bcf72
        center.x=getWidth()/2+moveX;
        if(moveY>getHeight()/2){
            moveY=getHeight()/2;
        }
<<<<<<< HEAD

       // Log.e("center",center.x+" "+center.y);
        if(flag){
            center.y=0+moveY;
        }
        path.moveTo(start.x, start.y);
        path.quadTo(center.x, center.y, end.x, end.y);
        canvas.drawPath(path, paint);

=======
        center.y=getHeight()/10+moveY;
       // Log.e("center",center.x+" "+center.y);
        path.moveTo(start.x, start.y);
        path.quadTo(center.x, center.y, end.x, end.y);
        canvas.drawPath(path, paint);
>>>>>>> 9d377006e517cfae9af4d17494fa392c538bcf72
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
<<<<<<< HEAD
                alpha=10;
                flag=true;
                x= (int) event.getX();
                y= (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                 if(alpha<70){
                     alpha=alpha+3;
                 }
                moveX=(int) event.getX()-x;
                moveY=(int)event.getY()-y;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                flag=false;
                //path.reset();
                while(center.y>0){
                    center.y=center.y-1;
                    invalidate();
                }
                break;
=======
                x= (int) event.getX();
                y= (int) event.getY();
                alpha=10;
            case MotionEvent.ACTION_MOVE:


                moveX=(int) event.getX()-x;
                moveY=(int)event.getY()-y;
                path.reset();
                invalidate();
                break;

>>>>>>> 9d377006e517cfae9af4d17494fa392c538bcf72

        }
        return  true;
    }
}
