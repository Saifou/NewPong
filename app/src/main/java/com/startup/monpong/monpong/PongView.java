package com.startup.monpong.monpong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;

import static android.content.Context.VIBRATOR_SERVICE;


/**
 * Created by Boss on 10/04/2018.
 */

public class PongView extends View implements View.OnTouchListener{

    int ballY;
    int ballX;
    int speedY;
    int speedX;
    float radius;
    Paint mypaint;
    float rectLeft;
    float rectTop;
    float rectRight;
    float rectBottom;
    float axeXPaddle;
    getSystemService(Context.VIBRATOR_SERVICE);
    Vibrator vb;


    public PongView(Context context) {
        super(context);


         this.setOnTouchListener(this);
         ballY = 200;
         ballX = 200;
         speedY = 20;
         speedX = 25;
         radius = 10f;
         mypaint = new Paint(50);
         rectLeft = 50f;
         rectTop = 2250f;
         rectRight = 500f;
         rectBottom = 2300f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(ballX,ballY,radius,mypaint);
        canvas.drawRect(rectLeft, rectTop, rectRight, rectBottom, mypaint);
        updateCanvas();

    }

    public void updateCanvas(){
        this.ballX += speedX;
        this.ballY += speedY;

        if(this.ballX < 0 || this.ballX > 1400){
            this.speedX *= -1;
        }
        if(this.ballY < 0 || this.ballY > 2600){
            this.speedY *= -1;
        }
        if((this.ballY > rectTop) && (ballX > rectLeft && ballX < rectRight)){
            vb.vibrate(100l);
            this.speedY *= -1;
        }


        invalidate();
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        rectLeft = motionEvent.getX()-250;
        rectRight = motionEvent.getX()+200;


        return true;
    }
}
