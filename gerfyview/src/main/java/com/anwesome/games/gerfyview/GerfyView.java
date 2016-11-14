package com.anwesome.games.gerfyview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 14/11/16.
 */
public class GerfyView extends View{
    private int DELAY = 50,R_SCALE = 36,H_SCALE = 6,MAX_SCALE=3;
    private float ry = 0;
    private String color = "#2196F3";
    private int textSize = 60,textColor = Color.BLACK,w,h;
    private String text="";
    private OnClickListener clickListener;
    private boolean shouldAnimate = false,loaded = false;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public GerfyView(Context context, AttributeSet attrs) {
        super(context,attrs);
    }
    public GerfyView(Context context) {
        super(context);
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }
    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
    public void onDraw(Canvas canvas) {

        paint.setColor(Color.parseColor(color));
        canvas.save();
        canvas.translate(w/2,h/2);
        Path path = new Path();
        path.moveTo(-w/2,h/6);
        drawVerticalLines(path,-w/2,h/H_SCALE,h/MAX_SCALE,-1);
        drawCurve(path,0,-h/H_SCALE,w/2,ry,180);
        drawVerticalLines(path,w/2,-h/H_SCALE,h/MAX_SCALE,1);
        drawCurve(path,0,h/H_SCALE,w/2,ry,0);
        canvas.drawPath(path,paint);
        canvas.restore();
        paint.setTextSize(textSize);
        float textSize = paint.measureText(getText());
        paint.setColor(textColor);
        canvas.drawText(text,w/2-textSize/2,7*h/12-textSize/15,paint);

        if(shouldAnimate) {
            ry+=h/(R_SCALE);
            if(ry>=(h/3-h/H_SCALE)){
                ry = 0;
                shouldAnimate = false;
                this.clickListener.onClick(this);
            }
            try {
                Thread.sleep(DELAY);
            }
            catch (Exception ex) {

            }
            invalidate();
        }
    }
    public void setOnClickListener(OnClickListener listener) {
        this.clickListener = listener;

    }
    private void drawVerticalLines(Path path,float x,float y,float h,float dir) {
        path.lineTo(x,y+h*dir);
    }
    private void drawCurve(Path path,float midX,float midY,float rx,float ry,int startDeg) {
        for(int i=startDeg;i<=startDeg+180;i++) {
            float x = midX+(float)(rx*Math.cos(i*Math.PI/180));
            float y = midY+(float)(ry*Math.sin(i*Math.PI/180));
            path.lineTo(x,y);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            if(!shouldAnimate) {
                shouldAnimate = true;
                postInvalidate();
            }
        }
        return true;
    }
    public void onLayout(boolean changed,int a,int b,int w,int h) {
        if(!loaded) {
            this.w = w;
            this.h = h;
            loaded = true;
        }
    }

}
