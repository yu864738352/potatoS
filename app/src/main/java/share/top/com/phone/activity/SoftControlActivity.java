package share.top.com.phone.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

import share.top.com.phone.R;
import share.top.com.phone.coustom.MyChartView;
import share.top.com.phone.coustom.MyPhoneProgressBar;
import share.top.com.phone.utils.MemoryUtil;
import share.top.com.phone.utils.RunAppUtil;

public class SoftControlActivity extends BaseActivity implements View.OnClickListener {
    private ImageView title_leftbtn;
    private MyPhoneProgressBar my_progress_in, my_progress_out;
    private MyChartView mMyChartView;
    private RelativeLayout All_Layout, System_Layout, User_layout;
    private TextView nei, wai;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_soft_control);
    }

    @Override
    public void initView() {
        nei = (TextView) findViewById(R.id.nei);
        wai = (TextView) findViewById(R.id.wai);
        title_leftbtn = (ImageView) findViewById(R.id.title_leftbtn);
        my_progress_in = (MyPhoneProgressBar) findViewById(R.id.my_progress_in);
        my_progress_out = (MyPhoneProgressBar) findViewById(R.id.my_progress_out);
        mMyChartView = (MyChartView) findViewById(R.id.mMyChartView);
        All_Layout = (RelativeLayout) findViewById(R.id.All_Layout);
        All_Layout.setOnClickListener(this);
        System_Layout = (RelativeLayout) findViewById(R.id.System_Layout);
        System_Layout.setOnClickListener(this);
        User_layout = (RelativeLayout) findViewById(R.id.User_layout);
        User_layout.setOnClickListener(this);
    }

    @Override
    public void beforInitView() {

    }

    MemoryUtil util;

    @Override
    public void afterInitView() {
        util = MemoryUtil.getInstance();
        setActionBar("软件管理", false, R.mipmap.btn_homeasup_default);
        title_leftbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.filetype_in_anim, R.anim.filetype_out_anim);
            }
        });
        nei.setText("手机内置内存：   " + unit((double) util.getRomAvailableSize()) + " / " + unit((double)
                util.getRomTotalSize()));
        my_progress_in.start((double) util.getRomAvailableSize(), (double) util.getRomTotalSize());
        int i[] = util.getRamTotalSize();
        my_progress_out.start(i[2], i[0]);
        wai.setText("手机外置内存：   " + unit(i[2] * 1024) + " / " + unit(i[0] * 1024));
        mMyChartView.start(0.5);
    }

    String fileSizeString;

    public String unit(final double fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        if (fileS == 0) {
            fileSizeString = 0 + "B";
        } else if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.All_Layout:
                Intent intent1 = new Intent(SoftControlActivity.this, SoftActivity.class);
                intent1.putExtra("id", 1);
                startActivity(intent1);
                break;
            case R.id.System_Layout:
                Intent intent2 = new Intent(SoftControlActivity.this, SoftActivity.class);
                intent2.putExtra("id", 2);
                startActivity(intent2);
                break;
            case R.id.User_layout:
                Intent intent3 = new Intent(SoftControlActivity.this, SoftActivity.class);
                intent3.putExtra("id", 3);
                startActivity(intent3);
                break;
        }
    }
}
