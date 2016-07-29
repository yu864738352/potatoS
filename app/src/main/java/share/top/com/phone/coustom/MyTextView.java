package share.top.com.phone.coustom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by ZHOU on 2016/2/27.
 */
public class MyTextView extends TextView {
    private Paint mPaint;
    public int index;
    private Paint paint;
    private int colors[] = {0xffea0000, 0xffe1e100, 0xec0000};
    private Random random = new Random();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (index == 0) {
                    invalidate();
                } else {
                    //  setText(index + "");
                    invalidate();
                }
            }
        }
    };

    public void start() {
        index = 3;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (index != 0) {
                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    --index;
                    mHandler.sendEmptyMessage(1);
                }
            }
        }).start();
    }


    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        int d = (int) random.nextInt(2);
        mPaint.setColor(colors[d]);
        mPaint.setAntiAlias(true);
        paint = new Paint();
        paint.setTextSize(50);
        paint.setAntiAlias(true);
        paint.setColor(0xff000000);
        canvas.drawCircle(getWidth() / 2, getWidth() / 2, getWidth() / 2, mPaint);
        canvas.drawText(index + "", getWidth() / 2 - 20, getWidth() / 2 + 20, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int min = Math.min(width, height);
        setMeasuredDimension(min, min);
    }

}
