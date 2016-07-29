package share.top.com.phone.activity;

import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import share.top.com.phone.R;
import share.top.com.phone.adapter.PhoneAddAdapter;
import share.top.com.phone.beans.AppInfo;
import share.top.com.phone.coustom.MyProgressBar;
import share.top.com.phone.utils.RunAppUtil;

public class PhoneAddActivity extends BaseActivity {

    private TextView phoneName;
    private TextView phoneVersion;
    private ImageView title_leftbtn;
    private ListView addList;
    private Button btn;
    private MyProgressBar my_progress;
    private Button show;
    private CheckBox All_Check;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_phone_add);
    }


    @Override
    public void initView() {
        my_progress = (MyProgressBar) findViewById(R.id.my_progress);
        title_leftbtn = (ImageView) findViewById(R.id.title_leftbtn);
        phoneName = (TextView) findViewById(R.id.phoneName);
        phoneVersion = (TextView) findViewById(R.id.phoneVersion);
        addList = (ListView) findViewById(R.id.addList);
        btn = (Button) findViewById(R.id.btn);
        show = (Button) findViewById(R.id.show);
        All_Check = (CheckBox) findViewById(R.id.All_Check);
    }

    @Override
    public void beforInitView() {

    }

    int index = 0;

    @Override
    public void afterInitView() {
        setActionBar("手机加速", false, R.mipmap.btn_homeasup_default);
        phoneName.setText(Build.MODEL);
        phoneVersion.setText(Build.BRAND);
        title_leftbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.filetype_in_anim, R.anim.filetype_out_anim);
            }
        });
        final RunAppUtil util = RunAppUtil.getInstance(PhoneAddActivity.this);
        ArrayList<AppInfo> list = util.getAll_List();
        final PhoneAddAdapter addAdapter = new PhoneAddAdapter(this, list);
        addList.setAdapter(addAdapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_progress.startAnim(100);
                ArrayList<AppInfo> infos = util.getAll_List();
                for (int i = 0; i < infos.size(); i++) {
                    if (infos.get(i).isCheck_flag()) {
                        util.killBackGroundApp(infos.get(i).getPackgeName());
                        infos.remove(i);
                        i--;
                        addAdapter.UpdateWork(infos);
                    }
                }
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == 0) {
                    addAdapter.UpdateWork(util.getUser_List());
                    addAdapter.notifyDataSetChanged();
                    index++;
                    show.setText("显示系统进程");
                } else {
                    addAdapter.UpdateWork(util.getSystem_List());
                    addAdapter.notifyDataSetChanged();
                    index--;
                    show.setText("显示用户进程");
                }
            }
        });
        All_Check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    for (int i = 0; i < util.getAll_List().size(); i++) {
                        util.getAll_List().get(i).setCheck_flag(true);
                        addAdapter.UpdateWork(util.getAll_List());
                    }
                }else{
                    for (int i = 0; i < util.getAll_List().size(); i++) {
                        util.getAll_List().get(i).setCheck_flag(false);
                        addAdapter.UpdateWork(util.getAll_List());
                    }
                }
            }
        });
    }
}
