package share.top.com.phone.coustom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by ZHOU on 2016/2/29.
 */
public class MyProgressBar extends ProgressBar {
    private Paint mPaint;

    private Context mContext;

    public MyProgressBar(Context context) {
        super(context);
        this.mContext = context;
    }

    public MyProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public MyProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }


    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(60);
        canvas.drawLine(10, 10, degree, 0, mPaint);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int max = Math.max(width, height);
        setMeasuredDimension(max, max);
    }

    static boolean isRunning = false;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                invalidate();
                setProgress(degree);
            }
        }
    };

    int degree;
    int person;

    public void startAnim(int sum) {
        person = sum;
        if (!isRunning) {
            isRunning = true;
            Log.i("msg", isRunning + "");
            degree = 50;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (degree != 0) {
                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {
                        }
                        degree--;
                        if (degree <= 0) {
                            degree = 0;
                        }
                        mHandler.sendEmptyMessage(1);
                    }
                    while (degree < person / 2) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        degree++;
                        mHandler.sendEmptyMessage(1);
                    }
                    isRunning = false;
                }
            }).start();
        }
    }
}
