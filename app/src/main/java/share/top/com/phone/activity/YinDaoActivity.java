package share.top.com.phone.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import share.top.com.phone.R;
import share.top.com.phone.adapter.MyPagerAdpater;

public class YinDaoActivity extends BaseActivity {

    private ViewPager mViewPager;
    private View[] points = new View[3];
    private int isshow = 0;

    @Override
    public void setContentLayout() {
        if (getData() == 0) {
            save(1);
            setContentView(R.layout.activity_yin_dao);
            isshow = 0;
        } else {
            setContentView(R.layout.activity_logo);
            isshow = 1;
        }
    }

    public void save(int i) {
        //1、创建文件  参数一、 代表文件名字，参数二 ，代表是否可以拼接
        SharedPreferences p = this.getSharedPreferences("first", 0);
        //2、编辑文件
        SharedPreferences.Editor e = p.edit();
        //3、储存数据
        e.putInt("version", i);
        e.commit();
    }

    public int getData() {
        SharedPreferences p = getSharedPreferences("first", 0);
        int version = p.getInt("version", 0);
        return version;
    }

    @Override
    public void initView() {
        if (isshow == 0) {
            mViewPager = (ViewPager) findViewById(R.id.viewPager);
            points[0] = findViewById(R.id.oneView);
            //   points[0].setBackgroundResource(R.mipmap.adware_style_selected);
            points[1] = findViewById(R.id.twoView);
            points[2] = findViewById(R.id.threeView);
        } else {

        }
    }

    @Override
    public void beforInitView() {

    }

    ArrayList<View> list = new ArrayList<>();

    @Override
    public void afterInitView() {
        if (isshow == 0) {
            View view = getLayoutInflater().inflate(R.layout.one_layout, null);
            View view2 = getLayoutInflater().inflate(R.layout.two_layout, null);
            View view3 = getLayoutInflater().inflate(R.layout.three_layout, null);
            TextView in = (TextView) view3.findViewById(R.id.in);
            in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(YinDaoActivity.this, LogoActivity.class));
                }
            });
            list.add(view);
            list.add(view2);
            list.add(view3);
            MyPagerAdpater adapter = new MyPagerAdpater(this, list);
            mViewPager.setAdapter(adapter);
            mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    //参数1:=1 代表松开，=0 代表按下 ,参数2 代表动画的可见度 参数3：代表滑动的坐标
                    change_PointColor(viewpager_index, position % points.length);
                    viewpager_index = position % points.length;
                    if (position > list.size()) {
                        finish();
                        startActivity(new Intent(YinDaoActivity.this, YinDaoActivity.class));
                    }
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        } else {
            startActivity(new Intent(YinDaoActivity.this, LogoActivity.class));
            overridePendingTransition(R.anim.activity_in_anim, R.anim.activity_out_anim);
            finish();
        }
    }

    int viewpager_index = 0;


    public void change_PointColor(int lastIndex, int newIndex) {
        //老的坐标变成黑色,adware_style_default
        points[lastIndex].setBackgroundResource(R.mipmap.adware_style_default);
        points[newIndex].setBackgroundResource(R.mipmap.adware_style_selected);
    }
}
