package share.top.com.phone;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import share.top.com.phone.activity.BaseActivity;
import share.top.com.phone.activity.CellActivity;
import share.top.com.phone.activity.FileInfoActivity;
import share.top.com.phone.activity.MessageActivity;
import share.top.com.phone.activity.PhoneAddActivity;
import share.top.com.phone.activity.SettingActivity;
import share.top.com.phone.activity.SoftControlActivity;
import share.top.com.phone.activity.WasteActivity;
import share.top.com.phone.adapter.ContentModelAdapter;
import share.top.com.phone.beans.ContentModel;
import share.top.com.phone.coustom.MyView;
import share.top.com.phone.utils.MemoryUtil;
import share.top.com.phone.utils.RunAppUtil;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private RelativeLayout left;
    private ListView left_listview;
    private List<ContentModel> list = new ArrayList<>();
    private MyView myView;
    private TextView textView;
    private TextView textMenu;
    private ImageView title_leftbtn;
    private ImageView title_rightbtn;
    MemoryUtil util;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        textMenu = (TextView) findViewById(R.id.textMenu);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        left = (RelativeLayout) findViewById(R.id.left);
        left_listview = (ListView) findViewById(R.id.left_listview);
        myView = (MyView) findViewById(R.id.CoustomView);
        textView = (TextView) findViewById(R.id.textView);
        title_leftbtn = (ImageView) findViewById(R.id.title_leftbtn);
        title_rightbtn = (ImageView) findViewById(R.id.title_rightbtn);
        util = MemoryUtil.getInstance();
    }

    public int[] colors = {0xE63231, 0xFFBB34, 0x65CC33, 0x000000};
    private Random random = new Random();
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                int index = colors[(int) (random.nextInt(3))];
                textMenu.setTextColor(index);
            }
        }
    };

    @Override
    public void beforInitView() {

    }


    double ram_percent;
    int[] ram_info;


    public void updateRam() {
        RunAppUtil utils = RunAppUtil.getInstance(this);
        ram_info = util.getRamTotalSize();
        ram_percent = (ram_info[2] / (double) (ram_info[0]));
        textView.setText((int) (ram_percent * 100) + "%");
        myView.startMyAnim(ram_percent);
        utils.killAllBackGroundApp();
    }

    @Override
    public void afterInitView() {
        setActionBar("手机管家", true, R.mipmap.ic_launcher);
        initData();
        updateRam();
        title_leftbtn.setOnClickListener(this);
        title_rightbtn.setOnClickListener(this);
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRam();
            }
        });
        ContentModelAdapter adapter = new ContentModelAdapter(this, list);
        left_listview.setAdapter(adapter);
        left_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDrawerLayout.closeDrawers();
                if (position == 4) {
                    Intent intent = new Intent(MainActivity.this, FileInfoActivity.class);
                    startActivity(intent);
                } else if (position == 0) {
                    Intent intent = new Intent(MainActivity.this, PhoneAddActivity.class);
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(MainActivity.this, SoftControlActivity.class);
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(MainActivity.this, CellActivity.class);
                    startActivity(intent);
                } else if (position == 3) {
                    Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                    startActivity(intent);
                } else if (position == 5) {
                    Intent intent = new Intent(MainActivity.this, WasteActivity.class);
                    startActivity(intent);
                }
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mHandler.sendEmptyMessage(1);
                }
            }
        });
    }

    public void initData() {
        list.add(new ContentModel(R.mipmap.icon_rocket, "手机加速"));
        list.add(new ContentModel(R.mipmap.icon_softmgr, "软件管理"));
        list.add(new ContentModel(R.mipmap.icon_phonemgr, "手机检测"));
        list.add(new ContentModel(R.mipmap.icon_telmgr, "通讯大全"));
        list.add(new ContentModel(R.mipmap.icon_filemgr, "文件管理"));
        list.add(new ContentModel(R.mipmap.icon_sdclean, "垃圾管理"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_leftbtn:
                mDrawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.title_rightbtn:
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                break;
        }
    }
}
