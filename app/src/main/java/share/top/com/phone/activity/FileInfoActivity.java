package share.top.com.phone.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import me.jamesxu.giftrainview.GiftRainView;
import share.top.com.phone.R;
import share.top.com.phone.beans.FileInfo;
import share.top.com.phone.utils.FileUtil;
import share.top.com.phone.utils.MemoryUtil;

public class FileInfoActivity extends BaseActivity {
    private ImageView title_leftbtn;
    private GiftRainView giftRainView;
    private TextView AllSize;
    private int index = 0;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_file_info);
    }

    @Override
    public void initView() {
        if (index == 0) {
            title_leftbtn = (ImageView) findViewById(R.id.title_leftbtn);
            giftRainView = (GiftRainView) findViewById(R.id.gift_rain_view);
            giftRainView.setImages(R.mipmap.ic_launcher, R.mipmap.icon_sdclean);
            giftRainView.startRain();
            AllSize = (TextView) findViewById(R.id.AllSize);
        }
    }

    Thread TypeThread;
    FileUtil fileUtils;
    ArrayList<FileInfo> All_List;
    ArrayList<FileInfo> Img_List;
    ArrayList<FileInfo> doc_List;
    ArrayList<FileInfo> VIOD_List;
    ArrayList<FileInfo> MUSIC_List;
    ArrayList<FileInfo> APK_List;
    ArrayList<FileInfo> ZIP_List;

    public void getFileTypeToSize() {
        TypeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                fileUtils = FileUtil.getIntance();
                APK_List = fileUtils.getAPK_List();
                Img_List = fileUtils.getIMG_List();
                MUSIC_List = fileUtils.getMUSIC_List();
                doc_List = fileUtils.getTXT_List();
                ZIP_List = fileUtils.getZIP_List();
                VIOD_List = fileUtils.getVIOD_List();
                All_List = fileUtils.getALL_List();
            }
        });
        TypeThread.start();
        mHandler.sendEmptyMessage(2);

    }

    @Override
    public void beforInitView() {

    }
    FileUtil fileUtil = FileUtil.getIntance();
    double sum;
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                FileInfo info = (FileInfo) msg.obj;
                sum += info.getFile().length();
                Log.i("tag", info.getFile() + "");
                AllSize.setText("已发现:" + unit(sum));
            } else if (msg.what == 2) {
//                finish();
            } else if (msg.what == 0) {
                AllSize.setEnabled(true);
                giftRainView.stopRainNow();
                Intent intent = new Intent(FileInfoActivity.this, FileTypeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.filetype_in_anim, R.anim.filetype_out_anim);
                Log.i("dsadadadadadadada", "flag执行000909090909090" );
                finish();
            }
        }
    };
    String fileSizeString = "";
    public Thread thread;

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
    public void afterInitView() {
        setActionBar("文件管理", false, R.mipmap.btn_homeasup_default);
        title_leftbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.activity_in_anim, R.anim.activity_out_anim);
            }
        });
        if (index == 0) {
            final MemoryUtil util = MemoryUtil.getInstance();
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
//                    info.setCheck(false);
                    fileUtil.Work(util.getInnerSdCard(), new FileUtil.callBackInterface() {
                        @Override
                        public void ToUpdate(FileInfo info) {
                            //发送消息给主线程 通知更新ui
                            Message message = new Message();
                            message.what = 1;
                            message.obj = info;
                            mHandler.sendMessage(message);
                        }
                        @Override
                        public void endOpen(int i) {
                            Log.i("msg", "回调方法执行了-------------------------");
//                            if (!info.isCheck())
                            if (i == 0)
                                mHandler.sendEmptyMessage(0);
                            else if (i == 1)
                                mHandler.sendEmptyMessage(2);
                            System.gc();
                        }
                    });
//                    info.setCheck(true);
                    Log.i("msg", "执行完成");
                }
            });
            getFileTypeToSize();
            thread.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fileUtil.setEnd_Flag(true);
    }
}
