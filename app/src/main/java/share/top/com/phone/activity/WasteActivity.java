package share.top.com.phone.activity;

import android.os.Handler;
import android.os.Message;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import share.top.com.phone.R;
import share.top.com.phone.adapter.PhoneCleanAdapter;
import share.top.com.phone.beans.ClearAppBean;
import share.top.com.phone.db.DBManager;
import share.top.com.phone.utils.FileUtil;

public class WasteActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    private ImageView title_leftbtn;
    private ListView phone_clean_listview;
    private TextView phone_clean_totalsize;
    private Button phone_clean_button;
    private CheckBox phone_clean_cb;
    private ProgressBar phone_clean_progressbar;
    private DBManager manager;
    private FileUtil util;
    private PhoneCleanAdapter adapter;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_waste);
    }

    @Override
    public void initView() {
        title_leftbtn = (ImageView) findViewById(R.id.title_leftbtn);
        phone_clean_listview = (ListView) findViewById(R.id.phone_clean_listview);
        phone_clean_button = (Button) findViewById(R.id.phone_clean_button);
        phone_clean_totalsize = (TextView) findViewById(R.id.phone_clean_totalsize);
        phone_clean_cb = (CheckBox) findViewById(R.id.phone_clean_cb);
        phone_clean_progressbar = (ProgressBar) findViewById(R.id.phone_clean_progressbar);
        phone_clean_cb.setChecked(true);
        util = FileUtil.getIntance();
        manager = DBManager.getInstance(this);
    }

    /**
     * 垃圾总共大小
     */
    private long totalSize;
    private ArrayList<ClearAppBean> infos;

    @Override
    public void beforInitView() {

    }

    @Override
    public void afterInitView() {
        setActionBar("垃圾清理", false, R.mipmap.btn_homeasup_default);
        title_leftbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.filetype_in_anim, R.anim.filetype_out_anim);
            }
        });
        phone_clean_button.setOnClickListener(this);
        adapter = new PhoneCleanAdapter(this);
        phone_clean_listview.setAdapter(adapter);
        infos = manager.getPhoneRubbishfile(getBaseContext());
        asyncLoaddata();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                Log.e("lll", totalSize + "");
                phone_clean_totalsize.setText(Formatter.formatFileSize(getBaseContext(), totalSize) + "");
            } else if (msg.what == 2) {
                Log.i("lll", "怎么没有呢");
                phone_clean_progressbar.setVisibility(View.GONE);
                phone_clean_listview.setVisibility(View.VISIBLE);
                adapter.setList(infos);
                phone_clean_cb.setOnCheckedChangeListener(WasteActivity.this);
            }
        }
    };

    private void asyncLoaddata() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("lll", "进来了呢" + infos.size());
                for (int i = 0; i < infos.size(); i++) {
                    Log.i("lll", "进来了呢");
                    File file = new File(infos.get(i).getFilepath());
                    long size = util.getFileSize(file);
                    infos.get(i).setSize(size);
                    totalSize += infos.get(i).getSize();
                    handler.sendEmptyMessage(1);
                }
                handler.sendEmptyMessage(2);
            }
        }).start();
    }

    private void deleteFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        for (int i = 0; i < infos.size(); i++) {
                            if (infos.get(i).isClean()) {
                                File file = new File(infos.get(i).getFilepath());
                                long size = util.getFileSize(file);
                                totalSize -= size;
                                util.deleteFile(file);
                                infos.remove(i);
                                i--;
                            }
                        }
                        adapter.notifyDataSetChanged();
                        Log.e("tag", totalSize + "");
                        phone_clean_totalsize.setText(Formatter.formatFileSize(
                                getBaseContext(), totalSize) + "");
                    }

                });
            }
        }).start();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        for (ClearAppBean info : infos) {
            info.setClean(isChecked);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.phone_clean_button:
                deleteFile();
                break;
        }
    }
}
