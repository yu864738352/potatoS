package share.top.com.phone.coustom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import java.util.Random;

import share.top.com.phone.R;

/**
 * Created by ZHOU on 2016/2/27.
 */
public class MyIcon extends View {

    private Paint mPaint;//画笔
    private RectF rectF;//矩形
    private Rect rect;

    private Random random = new Random();
    int degree = 10;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            degree = random.nextInt(90) + 181;
            invalidate();
        }
    };

    public void startImageAnimation() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessage(1);
            }
        }).start();
    }

    public MyIcon(Context context) {
        super(context);
    }

    public MyIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    Paint paint;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.home_score_pressed_bg);
        mPaint = new Paint();
        paint = new Paint();
        mPaint.setAntiAlias(true);
        paint.setAntiAlias(true);
        paint.setColor(0xff000079);
        mPaint.setStrokeWidth(30);
        canvas.drawArc(rectF, 0, degree, true, paint);
        canvas.drawBitmap(bitmap, rect, rectF, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (width > height) {
            setMeasuredDimension(heightMeasureSpec, heightMeasureSpec);
            rect = new Rect();
            rectF = new RectF(getWidth() / 4, getWidth() / 4, height, height);
        } else {
            setMeasuredDimension(widthMeasureSpec, widthMeasureSpec);
            rect = new Rect();
            rectF = new RectF(getWidth() / 4, getWidth() / 4, width, width);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startImageAnimation();
//            ScaleAnimation animation = new ScaleAnimation(1, 0.7f, 1, 0.7f, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);
//            animation.setDuration(2000);
//            animation.setFillAfter(true);
//            this.startAnimation(animation);
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
//            ScaleAnimation animation = new ScaleAnimation(0.7f, 1, 0.7f, 1, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);
//            animation.setDuration(2000);
//            animation.setFillAfter(true);
//            this.startAnimation(animation);
            return true;
        }
        return false;
    }
}