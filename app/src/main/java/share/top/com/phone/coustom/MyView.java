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
 * Created by ZHOU on 2016/2/26.
 */
public class MyView extends View {
    int degree;//角度
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                invalidate();//重新绘制
            } else if (msg.what == 2) {
                invalidate();
            }
        }
    };

    boolean isRunning = false;//代表线程是否运行

    public synchronized void startMyAnim(final double percent) {
        final int per = (int) (percent * 360);
        if (!isRunning) {
            degree = per;
            isRunning = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (degree != 0) {
                        try {
                            Thread.sleep(10);
                        } catch (Exception e) {

                        }
                        degree -= 5;
                        if (degree < 0) {
                            degree = 0;
                        }
                        mHandler.sendEmptyMessage(1);
                    }
                    while (degree != per) {
                        try {
                            Thread.sleep(10);
                        } catch (Exception e) {

                        }
                        degree += 5;
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
    //作为布局使用必须使用两个或两个以上参数的构造函数

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    Paint paint;

    //绘制方法
    // Canvas 画布 提供给你绘制的画布
    //有画布决定画什么
    Paint paint1;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        paint1 = new Paint();
        paint.setColor(0xffFF8E00);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(35);//设置笔的宽度
        paint.setAntiAlias(true);//设置抗锯齿
        paint1.setAntiAlias(true);
        paint1.setColor(Color.GREEN);
        canvas.drawArc(rectF, -90, degree, false, paint);
    }

    /**
     * 测量控件在父布局的位置作为参数，同时也可以修改控件的大小
     */
    RectF rectF;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //参数 测量系数包括 =宽高的具体值  宽和高在父类中的模式
        //测量控件的宽和高
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        //宽>高  xml中高度自适应
        if (width > height) {
            //重新设置控件的宽和高
            setMeasuredDimension(heightMeasureSpec, heightMeasureSpec);
            rectF = new RectF(30, 30, height - 40, height - 40);
        } else {
            setMeasuredDimension(widthMeasureSpec, widthMeasureSpec);
            rectF = new RectF(30, 30, width - 40, width - 40);
        }
    }


}
