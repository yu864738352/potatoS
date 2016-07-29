package share.top.com.phone.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v4.view.ViewPager;
import android.telephony.CellInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import share.top.com.phone.R;
import share.top.com.phone.adapter.MessageViewPagerAdapter;
import share.top.com.phone.adapter.PersionNumberAdapter;
import share.top.com.phone.beans.Persion;

public class MessageActivity extends BaseActivity implements View.OnClickListener {
    private ImageView title_leftbtn;
    private ViewPager MessageViewPager;
    //viw1
    private LinearLayout onelayout, twolayout,
            threelayout, fourlayout, fivelayout,
            sixlayout, sevenlayout, eightlayout;
    //view2
    private ListView ViewPagerListView;
    private ProgressBar phone_clean_progressbar;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_message);
    }

    @Override
    public void initView() {
        title_leftbtn = (ImageView) findViewById(R.id.title_leftbtn);
        MessageViewPager = (ViewPager) findViewById(R.id.MessageViewPager);
        points[0] = findViewById(R.id.oneView);
        points[1] = findViewById(R.id.twoView);

    }

    @Override
    public void beforInitView() {

    }

    ArrayList<View> list = new ArrayList<>();
    private View points[] = new View[2];
    int index = 0;
    boolean isRunning = true;
    ArrayList<Persion> liss;

    @Override
    public void afterInitView() {
        setActionBar("通讯大全", false, R.mipmap.btn_homeasup_default);
        title_leftbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.filetype_in_anim, R.anim.filetype_out_anim);
            }
        });
        View view1 = getLayoutInflater().inflate(R.layout.viewpager_layout2, null);
        View view2 = getLayoutInflater().inflate(R.layout.viewpager_layout1, null);
        list.add(view2);
        list.add(view1);
        MessageViewPagerAdapter adapter = new MessageViewPagerAdapter(this, list);
        MessageViewPager.setAdapter(adapter);
        init(view2);
        initView2(view1);
        MessageViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                change_PointColor(index, position % points.length);
                index = position;
                if (position == points.length - 1) {
                    if (isRunning) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                liss = getContentProvider();
                                isRunning = false;
                                mHandler.sendEmptyMessage(1);
                            }
                        }).start();

                    }
                }
            }

            public Handler mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if (msg.what == 1) {
                        phone_clean_progressbar.setVisibility(View.GONE);
                        PersionNumberAdapter adapter1 = new PersionNumberAdapter(MessageActivity.this, liss);
                        ViewPagerListView.setAdapter(adapter1);
                    }
                }
            };

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void change_PointColor(int lastIndex, int newIndex) {
        //老的坐标变成黑色,adware_style_default  R.mipmap.adware_style_default
        points[lastIndex].setBackgroundResource(R.mipmap.adware_style_default);
        points[newIndex].setBackgroundResource(R.mipmap.adware_style_selected);
    }

    public void init(View view) {
        onelayout = (LinearLayout) view.findViewById(R.id.onelayout);
        onelayout.setOnClickListener(this);
        twolayout = (LinearLayout) view.findViewById(R.id.twolayout);
        twolayout.setOnClickListener(this);
        threelayout = (LinearLayout) view.findViewById(R.id.threelayout);
        threelayout.setOnClickListener(this);
        fourlayout = (LinearLayout) view.findViewById(R.id.fourlayout);
        fourlayout.setOnClickListener(this);
        fivelayout = (LinearLayout) view.findViewById(R.id.fivelayout);
        fivelayout.setOnClickListener(this);
        sixlayout = (LinearLayout) view.findViewById(R.id.sixlayout);
        sixlayout.setOnClickListener(this);
        sevenlayout = (LinearLayout) view.findViewById(R.id.sevenlayout);
        sevenlayout.setOnClickListener(this);
        eightlayout = (LinearLayout) view.findViewById(R.id.eightlayout);
        eightlayout.setOnClickListener(this);
    }

    public void initView2(View view1) {
        ViewPagerListView = (ListView) view1.findViewById(R.id.ViewPagerListView);
        phone_clean_progressbar = (ProgressBar) view1.findViewById(R.id.phone_clean_progressbar);
    }


    private Intent intent;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.onelayout:
                intent = new Intent(MessageActivity.this, ServerActivity.class);
                intent.putExtra("key", 1);
                startActivity(intent);
                break;
            case R.id.twolayout:
                intent = new Intent(MessageActivity.this, ServerActivity.class);
                intent.putExtra("key", 2);
                startActivity(intent);
                break;
            case R.id.threelayout:
                intent = new Intent(MessageActivity.this, ServerActivity.class);
                intent.putExtra("key", 3);
                startActivity(intent);
                break;
            case R.id.fourlayout:
                intent = new Intent(MessageActivity.this, ServerActivity.class);
                intent.putExtra("key", 4);
                startActivity(intent);
                break;
            case R.id.fivelayout:
                intent = new Intent(MessageActivity.this, ServerActivity.class);
                intent.putExtra("key", 5);
                startActivity(intent);
                break;
            case R.id.sixlayout:
                intent = new Intent(MessageActivity.this, ServerActivity.class);
                intent.putExtra("key", 6);
                startActivity(intent);
                break;
            case R.id.sevenlayout:
                intent = new Intent(MessageActivity.this, ServerActivity.class);
                intent.putExtra("key", 7);
                startActivity(intent);
                break;
            case R.id.eightlayout:
                intent = new Intent(MessageActivity.this, ServerActivity.class);
                intent.putExtra("key", 8);
                startActivity(intent);
                break;
        }
    }

    public ArrayList<Persion> getContentProvider() {
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse(ContactsContract.Contacts.CONTENT_URI + "");
        Cursor cursor = resolver.query(uri, null, null, null, null, null);
        ArrayList<Persion> lists = new ArrayList<>();
        while (cursor.moveToNext()) {
            int contactID = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            /**查询个人的都多个电话号码,,可能有多个电话号码   phones.moveToNext()*/
            Cursor phones = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactID, null, null);
            while (phones.moveToNext()) {
                String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                lists.add(new Persion(name, phoneNumber));
                Log.i("msg:", name + ":" + phoneNumber);
            }
            phones.close();
        }
        cursor.close();
        return lists;
    }
}
