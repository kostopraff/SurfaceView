package com.example.surfaceview;

import android.animation.ArgbEvaluator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.Random;

public class MyThread extends Thread {

    private boolean flag;
    private long startTime;
    private long prevRedrawTime;

    private ArgbEvaluator argbEvaluator;

    private Paint paint;
    private SurfaceHolder holder;
    MyThread(SurfaceHolder h){
        this.holder=h;
        this.flag=false;
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        argbEvaluator = new ArgbEvaluator();
    }

    public long getTime(){
        return System.nanoTime()/1000;
    }

    public void setRunning(boolean f){
        this.flag=f;
        prevRedrawTime=getTime();
    }

    @Override
    public void run() {
        Canvas canvas;
        while(flag){
            canvas=holder.lockCanvas();
            //Random r = new Random();
            //int color = Color.rgb(
            //        r.nextInt(255),
            //        r.nextInt(255),
            //        r.nextInt(255));
            int color = (int) argbEvaluator.evaluate((float)Math.random(), Color.RED, Color.BLUE);
            paint.setColor(color);
            canvas.drawCircle(
                    canvas.getWidth()/2,
                    canvas.getHeight()/2,
                    canvas.getWidth()/2-30,
                    paint);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            holder.unlockCanvasAndPost(canvas);
        }
    }
}

