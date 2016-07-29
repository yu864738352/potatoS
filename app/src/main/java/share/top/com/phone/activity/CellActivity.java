package share.top.com.phone.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Pattern;

import share.top.com.phone.R;
import share.top.com.phone.utils.MemoryUtil;

public class CellActivity extends BaseActivity {
    private ImageView title_leftbtn;
    private TextView HardName, HardVersion, HardAllMemory,
            UserHardMemory, HardCupName, HardCupCount,
            HardScP, HardCreame, HardJidai, HardRoot;
    private ProgressBar pb_battery;
    int level;
    int scale;
    private TextView tv_battery;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_cell);
    }

    MyBastReceiver br;
    int index;

    @Override
    public void initView() {
        HardName = (TextView) findViewById(R.id.HardName);
        HardVersion = (TextView) findViewById(R.id.HardVersion);
        HardAllMemory = (TextView) findViewById(R.id.HardAllMemory);
        UserHardMemory = (TextView) findViewById(R.id.UserHardMemory);
        HardCupName = (TextView) findViewById(R.id.HardCupName);
        HardCupCount = (TextView) findViewById(R.id.HardCupCount);
        HardScP = (TextView) findViewById(R.id.HardScP);
        HardCreame = (TextView) findViewById(R.id.HardCreame);
        HardJidai = (TextView) findViewById(R.id.HardJidai);
        HardRoot = (TextView) findViewById(R.id.HardRoot);
        pb_battery = (ProgressBar) findViewById(R.id.pb_battery);
        tv_battery = (TextView) findViewById(R.id.tv_battery);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        br = new MyBastReceiver();
        registerReceiver(br, filter);
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void afterInitView() {
        setActionBar("手机检测", false, R.mipmap.btn_homeasup_default);
        title_leftbtn = (ImageView) findViewById(R.id.title_leftbtn);
        title_leftbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.filetype_in_anim, R.anim.filetype_out_anim);
            }
        });
        HardName.setText("设备名称: " + Build.BRAND + "");
        HardVersion.setText("系统版本: android:" + Build.VERSION.RELEASE);
        HardCupName.setText("cup名称: " + Build.CPU_ABI);
        HardCupCount.setText("cup数量: " + getNumCores());
        HardJidai.setText("基带版本: " + getBaseband_Ver());
        HardRoot.setText("是否root: ");
        MemoryUtil util =  MemoryUtil.getInstance();
        int i[] = util.getRamTotalSize();
        HardAllMemory.setText("手机全部运行内存: " + unit(i[0] * 1024));
        int user = (i[0] * 1024) - (i[2] * 1024);
        UserHardMemory.setText("手机剩余内存: " + unit(user));
        HardScP.setText("手机分辨率: " + getScreenDisPlay());
        HardCreame.setText("相机分辨率:" + getCameraDisplay());
        if (isRoot()) {
            HardRoot.setText("是否root：" + "已经root");
        } else {
            HardRoot.setText("是否root：" + "没有root");
        }
    }


    private String getScreenDisPlay() {
        DisplayMetrics m = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(m);
        int height = m.heightPixels;
        int width = m.widthPixels;
        return height + "* " + width;
    }

    public String getCameraDisplay() {
        Camera camera = Camera.open();
        Camera.Parameters p = camera.getParameters();
        List<Camera.Size> list = p.getSupportedPreviewSizes();
        Log.i("msg", list.get(0).height + "" + list.get(0).width);
        return list.get(0).width + "*" + list.get(0).height;
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

    //CPU个数
    private int getNumCores() {
        //Private Class to display only CPU devices in the directory listing
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                //Check if filename is "cpu", followed by a single digit number
                if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }
        try {
            //Get directory containing CPU info
            File dir = new File("/sys/devices/system/cpu/");
            //Filter to only list the devices we care about
            File[] files = dir.listFiles(new CpuFilter());
            Log.d("TAG", "CPU Count: " + files.length);
            //Return the number of cores (virtual CPU devices)
            return files.length;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static String getBaseband_Ver() {
        String Version = "";
        try {
            Class cl = Class.forName("android.os.SystemProperties");
            Object invoker = cl.newInstance();
            Method m = cl.getMethod("get", new Class[]{String.class, String.class});
            Object result = m.invoke(invoker, new Object[]{"gsm.version.baseband", "no message"});
            Version = (String) result;
        } catch (Exception e) {
        }
        return Version;
    }

    int i;
    int j;

    class MyBastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
                //获取当前电量
                level = intent.getIntExtra("level", 0);
                //电量的总刻度
                scale = intent.getIntExtra("scale", 100);
                i = level;
                j = scale;
                MyThreadAddlevel(i, j);
                //把它转成百分比
                String ss = ((level * 100) / scale) + "%";
                tv_battery.setText(ss);
            }
        }
    }

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                //secondaryProgress
                pb_battery.setSecondaryProgress(index);
            }
        }
    };

    public void MyThreadAddlevel(final int levels, final int scales) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (index <= (levels * 100 / scales)) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    index++;
                    mHandler.sendEmptyMessage(1);
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(br);
    }

    /*判断手机是否root**/

    /**
     * 判断手机是否ROOT
     */
    public boolean isRoot() {
        boolean root = false;
        try {
            if ((!new File("/system/bin/su").exists())
                    && (!new File("/system/xbin/su").exists())) {
                root = false;
            } else {
                root = true;
            }
        } catch (Exception e) {
        }
        return root;
    }
}
