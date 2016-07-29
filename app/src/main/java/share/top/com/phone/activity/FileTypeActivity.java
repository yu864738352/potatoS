package share.top.com.phone.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import share.top.com.phone.R;

public class FileTypeActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout music_layout;
    private LinearLayout viod_layout;
    private LinearLayout zip_layout;
    private LinearLayout doc_layout;
    private LinearLayout apk_layout;
    private LinearLayout img_layout;
    private LinearLayout all_layout;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_file_type);
    }

    @Override
    public void initView() {
        music_layout = (LinearLayout) findViewById(R.id.music_layout);
        music_layout.setOnClickListener(this);
        viod_layout = (LinearLayout) findViewById(R.id.viod_layout);
        viod_layout.setOnClickListener(this);
        zip_layout = (LinearLayout) findViewById(R.id.zip_layout);
        zip_layout.setOnClickListener(this);
        doc_layout = (LinearLayout) findViewById(R.id.doc_layout);
        doc_layout.setOnClickListener(this);
        apk_layout = (LinearLayout) findViewById(R.id.apk_layout);
        apk_layout.setOnClickListener(this);
        img_layout = (LinearLayout) findViewById(R.id.img_layout);
        img_layout.setOnClickListener(this);
        all_layout = (LinearLayout) findViewById(R.id.all_layout);
        all_layout.setOnClickListener(this);
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void afterInitView() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private Intent intent;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.music_layout:
                intent = new Intent(FileTypeActivity.this, FileIndexTypeActivity.class);
                intent.putExtra("index", 1);
                startActivity(intent);
                break;
            case R.id.viod_layout:
                intent = new Intent(FileTypeActivity.this, FileIndexTypeActivity.class);
                intent.putExtra("index", 2);
                startActivity(intent);
                break;
            case R.id.zip_layout:
                intent = new Intent(FileTypeActivity.this, FileIndexTypeActivity.class);
                intent.putExtra("index", 3);
                startActivity(intent);
                break;
            case R.id.doc_layout:
                intent = new Intent(FileTypeActivity.this, FileIndexTypeActivity.class);
                intent.putExtra("index", 4);
                startActivity(intent);
                break;
            case R.id.apk_layout:
                intent = new Intent(FileTypeActivity.this, FileIndexTypeActivity.class);
                intent.putExtra("index", 5);
                startActivity(intent);
                break;
            case R.id.img_layout:
                intent = new Intent(FileTypeActivity.this, FileIndexTypeActivity.class);
                intent.putExtra("index", 6);
                startActivity(intent);
                break;
            case R.id.all_layout:
                intent = new Intent(FileTypeActivity.this, FileIndexTypeActivity.class);
                intent.putExtra("index", 7);
                startActivity(intent);
                break;
        }
    }
}
