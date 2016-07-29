package share.top.com.phone.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import share.top.com.phone.MainActivity;
import share.top.com.phone.R;
import share.top.com.phone.coustom.MyTextView;

public class LogoActivity extends BaseActivity {
    private MyTextView times;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_logo);
    }

    @Override
    public void initView() {
        times = (MyTextView) findViewById(R.id.times);
    }

    @Override
    public void beforInitView() {

    }

    static int timess = 3;
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                startActivity(new Intent(LogoActivity.this, MainActivity.class));
                finish();
            }
        }
    };

    int sleep = 2100;
    @Override
    public void afterInitView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.sendEmptyMessage(1);
            }
        }).start();
        times.start();
    }
}
