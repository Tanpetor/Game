package com.example.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.Button;

import java.util.Random;
import java.util.concurrent.Callable;

public class DrawThread extends Thread {

    private SurfaceHolder surfaceHolder;
    Button jump;
    Paint p = new Paint();
    Paint pa = new Paint();
    //jump = findView
    float tt = -1, t1 = 0, k = 0, tt1, n, sp = 4;
    Random rand = new Random();
    Paint backgroundPaint = new Paint();
    private volatile boolean running = true;//флаг для остановки потока

    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        p.setColor(Color.RED);
        pa.setColor(Color.BLACK);
        backgroundPaint.setColor(Color.WHITE);
        p.setStyle(Paint.Style.FILL);
    }

    public void requestStop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            Log.d("r","runrunrun");
            Canvas canvas = surfaceHolder.lockCanvas();

            if (canvas != null) {
                try {
                    canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
                    canvas.drawCircle(Content.sun.x, Content.sun.y, Content.sun.r, p);

                    if(Content.rec.right > 0) {
                        tt = (float)(Math.random() * 1000) + 200;
                        t1 += 1;
                        if(t1 == 1) {
                            k += 1;
                            tt1 = tt;
                        }
                    }
                    if(k == 1 && Content.rec.right > 0) {
                        canvas.drawRect(Content.rec.left, Content.rec.top, Content.rec.right, tt1, pa);
                        canvas.drawRect(Content.rec.left, tt1 + 300, Content.rec.right, Content.rec1.bottom1, pa);
                    }
                    if(Content.rec.right < 0) {
                        t1 = 0;
                        k = 0;
                        Content.rec.left = 1310 + 300;
                        Content.rec.right = 1400 + 300;
                    }


                    if(((Content.sun.y + Content.sun.r >= tt1 + 300) || ( Content.sun.y - Content.sun.r <= tt1)) && Content.sun.x < Content.rec.right && Content.sun.x >Content.rec.left) {
                        n = 0;
                    } else {
                        if(Content.sun.x > Content.rec.left && Content.sun.x < Content.rec.right) {
                            Content.count.count += 1;
                        }
                        Content.count.count += 1;
                        Content.rec.left -= sp;
                        Content.rec.right -= sp;
                        Content.sun.y += 4;
                    }
                    //if(Content.count.count >= 3) sp += 2;
                    //if(Content.count.count >= 7) sp += 2;
                    //f(Content.count.count >= 10) sp += 2;
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }

    }
}