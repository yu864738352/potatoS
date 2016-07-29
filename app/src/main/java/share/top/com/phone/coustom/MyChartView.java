package share.top.com.phone.coustom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ZHOU on 2016/3/1.
 * 饼状图
 */
public class MyChartView extends View {

    private Paint mPaint;
    int degree = 0;
    private boolean isRunning = false;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                invalidate();
            }
        }
    };

    public void start(double sum) {
        final int per = (int) (sum * 360);
        if (!isRunning) {
            isRunning = true;
            degree = 0;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (degree != per) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        degree++;
                        if (degree > per) {
                            degree = per;
                        }
                        mHandler.sendEmptyMessage(1);
                    }
                    isRunning = false;
                }
            }).start();
        }
    }


    public MyChartView(Context context) {
        super(context);
    }

    public MyChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    RectF rectf;
    Paint mPaint2;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();//画笔
        mPaint2 = new Paint();
        mPaint2.setColor(0xff32CD32);
        mPaint2.setAntiAlias(true);
        mPaint.setColor(0xff000098);
        mPaint.setAntiAlias(true);
        canvas.drawCircle(getWidth() / 2, getWidth() / 2, getWidth() / 2, mPaint);
        canvas.drawArc(rectf, -90, degree, false, mPaint2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (width > height) {
            setMeasuredDimension(heightMeasureSpec, heightMeasureSpec);
            rectf = new RectF(0, 0, height, height);
        } else {
            setMeasuredDimension(widthMeasureSpec, widthMeasureSpec);
            rectf = new RectF(0, 0, width, width);
        }
    }
}
