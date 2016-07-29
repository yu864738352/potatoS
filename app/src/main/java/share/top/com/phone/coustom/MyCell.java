package share.top.com.phone.coustom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ZHOU on 2016/3/5.
 */
public class MyCell extends TextView {

    private Paint mPaint;
    private int degree = 50;//代表园的半径

    public MyCell(Context context) {
        super(context);
    }

    public MyCell(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    int heights;
    boolean isRunning = false;
    int width = 0;
    int edwidth;

    //开始x,y坐标，结束x,y坐标
    //TODO
    public void startMove(int startwidth, int startheight, final int endwidth) {
        if (!isRunning) {
            isRunning = true;
            heights = startheight;
            width = startwidth;
            edwidth = endwidth;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (width < endwidth) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        width++;
                        if (width == endwidth) {
                            width = 0;
                        }
                        mHandler.sendEmptyMessage(1);
                    }
                    isRunning = false;
                }
            }).start();
        }
    }

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                invalidate();
            }
        }
    };
    RectF rectF;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
//        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 50, mPaint);
        canvas.drawRect(rectF, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int min = Math.min(width, height);
        rectF = new RectF(width, heights / 2, edwidth, heights / 2);
        setMeasuredDimension(min, min);
    }
}
