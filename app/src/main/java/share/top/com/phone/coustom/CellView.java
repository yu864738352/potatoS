package share.top.com.phone.coustom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by ZHOU on 2016/3/4.
 */
public class CellView extends TextView {

    private Paint mPaint;
    private RectF mRectF;

    public CellView(Context context) {
        super(context);
    }

    public CellView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CellView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        //0xffFE8A01
        mPaint.setColor(Color.GREEN);
        //mPaintout.setColor(0x00AA01);
        mPaint.setAntiAlias(true);
        canvas.drawRect(mRectF, mPaint);
        Log.i("msg", "执行到这儿了");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int max = Math.max(width, height);
        mRectF = new RectF(200, 400, max, max);
        setMeasuredDimension(max, max);
    }
}
