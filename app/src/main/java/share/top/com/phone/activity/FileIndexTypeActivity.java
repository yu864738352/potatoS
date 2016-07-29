package share.top.com.phone.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import share.top.com.phone.R;
import share.top.com.phone.adapter.FileInfoAdapter;
import share.top.com.phone.beans.FileInfo;
import share.top.com.phone.utils.FileUtil;

public class FileIndexTypeActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private ImageView title_leftbtn;
    private ListView indexType;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_file_index_type);
    }

    int index = 0;

    @Override
    public void initView() {
        title_leftbtn = (ImageView) findViewById(R.id.title_leftbtn);
        index = getIntent().getIntExtra("index", 0);
        indexType = (ListView) findViewById(R.id.indexType);
    }

    @Override
    public void beforInitView() {

    }

    FileInfoAdapter adapter;

    @Override
    public void afterInitView() {
        if (index == 1) {
            setActionBar("音乐文件", false, R.mipmap.btn_homeasup_default);
        } else if (index == 2) {
            setActionBar("视频文件", false, R.mipmap.btn_homeasup_default);
        } else if (index == 3) {
            setActionBar("压缩文件", false, R.mipmap.btn_homeasup_default);
        } else if (index == 4) {
            setActionBar("文本文件", false, R.mipmap.btn_homeasup_default);
        } else if (index == 5) {
            setActionBar("APK文件", false, R.mipmap.btn_homeasup_default);
        } else if (index == 6) {
            setActionBar("图片文件", false, R.mipmap.btn_homeasup_default);
        } else if (index == 7) {
            setActionBar("全部文件", false, R.mipmap.btn_homeasup_default);
        }
        title_leftbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.filetype_in_anim, R.anim.filetype_out_anim);
            }
        });
        getFileTypeToSize();
        indexType.setOnItemClickListener(this);
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
    FileUtil fileUtil = FileUtil.getIntance();

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
        mHandler.sendEmptyMessage(1);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (index == 1) {
                    adapter = new FileInfoAdapter(FileIndexTypeActivity.this, MUSIC_List, R.mipmap.icon_wma);
                    indexType.setAdapter(adapter);
                } else if (index == 2) {
                    adapter = new FileInfoAdapter(FileIndexTypeActivity.this, VIOD_List, R.mipmap.icon_video);
                    indexType.setAdapter(adapter);
                } else if (index == 3) {
                    adapter = new FileInfoAdapter(FileIndexTypeActivity.this, ZIP_List, R.mipmap.icon_zip);
                    indexType.setAdapter(adapter);
                } else if (index == 4) {
                    adapter = new FileInfoAdapter(FileIndexTypeActivity.this, doc_List, R.mipmap.icon_text_richtext);
                    indexType.setAdapter(adapter);
                } else if (index == 5) {
                    adapter = new FileInfoAdapter(FileIndexTypeActivity.this, APK_List, R.mipmap.icon_archive);
                    indexType.setAdapter(adapter);
                } else if (index == 6) {
                    adapter = new FileInfoAdapter(FileIndexTypeActivity.this, Img_List, R.mipmap.icon_bmp);
                    indexType.setAdapter(adapter);
                } else if (index == 7) {
                    adapter = new FileInfoAdapter(FileIndexTypeActivity.this, All_List, R.mipmap.icon_ppt);
                    indexType.setAdapter(adapter);
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fileUtil.setEnd_Flag(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(FileIndexTypeActivity.this);
        builder.setTitle("提示").setMessage("删除").setCancelable(false).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (index == 1) {
                    MUSIC_List.get(position).getFile().delete();
                    MUSIC_List.remove(position);
                    adapter.UpdateAdapter(MUSIC_List);
                } else if (index == 2) {
                    VIOD_List.get(position).getFile().delete();
                    VIOD_List.remove(position);
                    adapter.UpdateAdapter(VIOD_List);
                } else if (index == 3) {
                    ZIP_List.get(position).getFile().delete();
                    ZIP_List.remove(position);
                    adapter.UpdateAdapter(ZIP_List);
                } else if (index == 4) {
                    doc_List.get(position).getFile().delete();
                    doc_List.remove(position);
                    adapter.UpdateAdapter(doc_List);
                } else if (index == 5) {
                    APK_List.get(position).getFile().delete();
                    APK_List.remove(position);
                    adapter.UpdateAdapter(APK_List);
                } else if (index == 6) {
                    Img_List.get(position).getFile().delete();
                    Img_List.remove(position);
                    adapter.UpdateAdapter(Img_List);
                } else if (index == 7) {
                    All_List.get(position).getFile().delete();
                    All_List.remove(position);
                    adapter.UpdateAdapter(All_List);
                }
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }
}

