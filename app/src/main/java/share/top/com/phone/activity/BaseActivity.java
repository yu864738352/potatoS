package share.top.com.phone.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import share.top.com.phone.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        setContentLayout();
        beforInitView();
        initView();
        afterInitView();
    }

    public abstract void setContentLayout();

    public abstract void initView();

    public abstract void beforInitView();

    public abstract void afterInitView();

    public void setActionBar(String title, boolean isVi, int left_res) {
        TextView title_centertext = (TextView) findViewById(R.id.title_centertext);
        ImageView title_rightbtn = (ImageView) findViewById(R.id.title_rightbtn);
        ImageView title_leftbtn = (ImageView) findViewById(R.id.title_leftbtn);
        title_centertext.setText(title);
        title_leftbtn.setImageResource(left_res);
        if (!isVi) {
            title_rightbtn.setVisibility(View.INVISIBLE);
        }
    }
}
