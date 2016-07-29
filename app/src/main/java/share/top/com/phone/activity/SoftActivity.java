package share.top.com.phone.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import share.top.com.phone.R;
import share.top.com.phone.adapter.ManagerSoftAdapter;
import share.top.com.phone.beans.AppInstall;
import share.top.com.phone.utils.FileManager;
import share.top.com.phone.utils.RunAppUtil;
import share.top.com.phone.utils.UnIntasllApp;

public class SoftActivity extends BaseActivity {

    private ImageView title_leftbtn;
    private ListView Soft_ListView;
    private CheckBox All_Check_Sotf;
    private Button Clean_Sotf_Clean;
    AdapterBroadcast adb;
    IntentFilter filter;
    int num;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_soft);
    }

    @Override
    public void initView() {
        title_leftbtn = (ImageView) findViewById(R.id.title_leftbtn);
        Soft_ListView = (ListView) findViewById(R.id.Soft_ListView);
        All_Check_Sotf = (CheckBox) findViewById(R.id.All_Check_Sotf);
        Clean_Sotf_Clean = (Button) findViewById(R.id.Clean_Sotf_Clean);
        adb = new AdapterBroadcast();
        filter = new IntentFilter();
        filter.addDataScheme("package");
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        registerReceiver(adb, filter);
    }

    @Override
    public void beforInitView() {

    }

    int dddd = 0;
    Intent intent;
    UnIntasllApp app;

    @Override
    public void afterInitView() {
        int id = getIntent().getIntExtra("id", 0);
        dddd = id;
        if (id == 1) {
            setActionBar("全部软件", false, R.mipmap.btn_homeasup_default);
            showSoft(1);
        } else if (id == 2) {
            showSoft(2);
            setActionBar("系统软件", false, R.mipmap.btn_homeasup_default);
        } else {
            showSoft(3);
            setActionBar("用户软件", false, R.mipmap.btn_homeasup_default);
        }
        title_leftbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.activity_in_anim, R.anim.activity_out_anim);
            }
        });
        final RunAppUtil util = RunAppUtil.getInstance(this);
        Clean_Sotf_Clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dddd == 1) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).isCheck_flag()) {
                            app = new UnIntasllApp(SoftActivity.this);
                            app.UnIntasll(list.get(i).getAppPackageName());
                            num = i;
                        }
                    }
                } else if (dddd == 2) {
                    for (int i = 0; i < listSystem.size(); i++) {
                        if (listSystem.get(i).isCheck_flag()) {
                            app = new UnIntasllApp(SoftActivity.this);
                            app.UnIntasll(list.get(i).getAppPackageName());
                            num = i;
                        }
                    }
                } else if (dddd == 3) {
                    for (int i = 0; i < listUser.size(); i++) {
                        if (listUser.get(i).isCheck_flag()) {
                            app = new UnIntasllApp(SoftActivity.this);
                            app.UnIntasll(listUser.get(i).getAppPackageName());
                            num = i;
                        }
                    }
                }
            }
        });
        All_Check_Sotf.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (dddd == 1) {
                    if (isChecked) {
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setCheck_flag(true);
                            adapter.UpdateWork(list);
                        }
                    } else {
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setCheck_flag(false);
                            adapter.UpdateWork(list);
                        }
                    }
                } else if (dddd == 2) {
                    if (isChecked) {
                        for (int i = 0; i < listSystem.size(); i++) {
                            listSystem.get(i).setCheck_flag(true);
                            adapter.UpdateWork(listSystem);
                        }
                    } else {
                        for (int i = 0; i < listSystem.size(); i++) {
                            listSystem.get(i).setCheck_flag(false);
                            adapter.UpdateWork(listSystem);
                        }
                    }
                } else {
                    if (isChecked) {
                        for (int i = 0; i < listUser.size(); i++) {
                            listUser.get(i).setCheck_flag(true);
                            adapter.UpdateWork(listUser);
                        }
                    } else {
                        for (int i = 0; i < listUser.size(); i++) {
                            listUser.get(i).setCheck_flag(false);
                            adapter.UpdateWork(listUser);
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(adb);
    }

    ManagerSoftAdapter adapter;
    int index;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                dialog.cancel();
                Soft_ListView.setAdapter(adapter);
            } else if (msg.what == 2) {
                Soft_ListView.setAdapter(adapter);
                dialog.cancel();
            } else {
                Soft_ListView.setAdapter(adapter);
                dialog.cancel();
            }
        }
    };

    ArrayList<AppInstall> list;
    ArrayList<AppInstall> listSystem;
    ArrayList<AppInstall> listUser;
    private SpotsDialog dialog;
    FileManager manager;

    public void showSoft(int i) {
        index = i;
        manager = FileManager.getInstace(this);
        manager.getInstallApp();
        dialog = new SpotsDialog(SoftActivity.this);
        dialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (index == 1) {
                    list = manager.getAll_List();
                    adapter = new ManagerSoftAdapter(SoftActivity.this, list);
                    mHandler.sendEmptyMessage(1);
                } else if (index == 2) {
                    listSystem = manager.getSystem_List();
                    adapter = new ManagerSoftAdapter(SoftActivity.this, listSystem);
                    mHandler.sendEmptyMessage(2);
                } else {
                    listUser = manager.getUser_List();
                    adapter = new ManagerSoftAdapter(SoftActivity.this, listUser);
                    mHandler.sendEmptyMessage(3);
                }
            }
        }).start();
    }

    class AdapterBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //接收广播：设备上删除了一个应用程序包。
            if (dddd == 3) {
                listUser.remove(num);
                adapter.UpdateWork(listUser);
            } else if (dddd == 2) {
                listSystem.remove(num);
                adapter.UpdateWork(listSystem);
            } else if (dddd == 1) {
                list.remove(num);
                adapter.UpdateWork(list);
            }
        }
    }
}
