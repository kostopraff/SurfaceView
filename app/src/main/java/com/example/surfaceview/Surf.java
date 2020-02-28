package com.example.surfaceview;

import android.content.Context;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class Surf extends SurfaceView implements SurfaceHolder.Callback {
    MyThread mt;
    public Surf(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mt = new MyThread(getHolder());
        mt.setRunning(true);
        mt.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        mt.setRunning(false);
        while(retry){
            try {
                mt.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
