package share.top.com.phone.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import share.top.com.phone.R;
import share.top.com.phone.beans.togglebean;
import share.top.com.phone.notifi.MyNotification;

public class SettingActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    private ImageView title_leftbtn;
    private ToggleButton toggleone;
    private ToggleButton toggletwo;
    private ToggleButton togglethree;
    private togglebean bean = new togglebean();
    private boolean first;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_setting);
    }

    @Override
    public void initView() {
        title_leftbtn = (ImageView) findViewById(R.id.title_leftbtn);
        toggleone = (ToggleButton) findViewById(R.id.toggleone);
        toggleone.setOnCheckedChangeListener(this);
        toggletwo = (ToggleButton) findViewById(R.id.toggletwo);
        toggletwo.setOnCheckedChangeListener(this);
        togglethree = (ToggleButton) findViewById(R.id.togglethree);
        togglethree.setOnCheckedChangeListener(this);
    }

    private void setData() {
        preferences = this.getSharedPreferences("togglebuttonstatus", Context.MODE_PRIVATE);
        first = preferences.getBoolean("first", true);
        editor = preferences.edit();
        if (first) {
            getStatus();
        } else {
            bean.toggleone = preferences.getBoolean("s_one", false);
            bean.toggletwo = preferences.getBoolean("s_two", false);
            bean.togglethree = preferences.getBoolean("s_three", false);
            setToggButonStatus(bean);
        }
    }

    /*
         * 根据保存的参数设置每个ToggleButton的状态
         */
    private void setToggButonStatus(togglebean data) {
        toggleone.setChecked(data.toggleone);
        toggletwo.setChecked(data.toggletwo);
        togglethree.setChecked(data.togglethree);
    }

    /*
         * 获取每个ToggleButton的状态，并保存在status里面
         */
    private void getStatus() {
        bean.toggleone = toggleone.isChecked();
        bean.toggletwo = toggletwo.isChecked();
        bean.togglethree = togglethree.isChecked();
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void afterInitView() {
        setActionBar("设置", false, R.mipmap.btn_homeasup_default);
        title_leftbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //overridePendingTransition(R.anim.filetype_in_anim, R.anim.filetype_out_anim);
            }
        });
        setData();
    }

    MyNotification notification;

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()) {
            case R.id.toggleone:
                notification = MyNotification.getInstance(SettingActivity.this);
                if (isChecked) {
                    notification.MyF("开机启动已开启");
                }else{
                    notification.MyClean();
                }
                bean.setToggleone(isChecked);
                break;
            case R.id.toggletwo:
                bean.setToggletwo(isChecked);
                break;
            case R.id.togglethree:
                bean.setTogglethree(isChecked);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (first) {
            editor.putBoolean("first", false);
        }
        //关闭之前把数据写进去
        editor.putBoolean("s_one", bean.toggleone);
        editor.putBoolean("s_two", bean.toggletwo);
        editor.putBoolean("s_three", bean.togglethree);
        editor.commit();
    }
}
