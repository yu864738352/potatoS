package share.top.com.phone.coustom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ProgressBar;

/**
 * Created by ZHOU on 2016/3/1.
 */
public class MyPhoneProgressBar extends ProgressBar {

    private Paint mPaint;
    private Paint mPaint2;
    private int dregree = 0;
    private boolean isRunning = false;
    double percent = 0;

    public MyPhoneProgressBar(Context context) {
        super(context);
    }

    public MyPhoneProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPhoneProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                invalidate();
                setProgress(dregree);
            }
        }
    };

    double indes;
    double pers;

    public void start(final double index, final double per) {
        indes = index;
        pers = per;
        if (!isRunning) {
            isRunning = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //拿到比例
                    percent = index / pers * 100;
                    while (dregree < percent) {
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        dregree++;
                        mHandler.sendEmptyMessage(1);
                    }
                }
            }).start();
        }
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(0xffdf0024);
        mPaint2 = new Paint();
        mPaint2.setAntiAlias(true);
        mPaint2.setColor(0xffde42ec);
        canvas.drawLine(15, 0, 0, 0, mPaint2);
        canvas.drawLine(15, 0, 0, 0, mPaint);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int max = Math.max(width, height);
        setMeasuredDimension(max, max);
    }
}
