package share.top.com.phone.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import share.top.com.phone.R;
import share.top.com.phone.adapter.ServerAdapter;
import share.top.com.phone.beans.OperatorBean;
import share.top.com.phone.db.DBManager;

public class ServerActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private ImageView title_leftbtn;
    private ListView ServerListView;
    private DBManager manager;
    private ArrayList<OperatorBean> list;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_server);
    }

    @Override
    public void initView() {
        title_leftbtn = (ImageView) findViewById(R.id.title_leftbtn);
        ServerListView = (ListView) findViewById(R.id.ServerListView);
        manager = DBManager.getInstance(this);
    }

    public void Op() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                list = manager.meals("table1");
            }
        }).start();
    }

    @Override
    public void beforInitView() {

    }


    @Override
    public void afterInitView() {
        manager.getFile(this);
        int index = getIntent().getIntExtra("key", 0);
        ServerAdapter adapter;
        if (index == 1) {
            setActionBar("订餐电话", false, R.mipmap.btn_homeasup_default);
            list = manager.meals("table1");
            adapter = new ServerAdapter(this, list);
            ServerListView.setAdapter(adapter);
        } else if (index == 2) {
            setActionBar("公共服务", false, R.mipmap.btn_homeasup_default);
            list = manager.meals("table2");
            adapter = new ServerAdapter(this, list);
            ServerListView.setAdapter(adapter);
        } else if (index == 3) {
            setActionBar("运营商", false, R.mipmap.btn_homeasup_default);
            list = manager.meals("table3");
            adapter = new ServerAdapter(this,list);
            ServerListView.setAdapter(adapter);
        } else if (index == 4) {
            setActionBar("快递服务", false, R.mipmap.btn_homeasup_default);
            list = manager.meals("table4");
            adapter = new ServerAdapter(this, list);
            ServerListView.setAdapter(adapter);
        } else if (index == 5) {
            setActionBar("机票酒店", false, R.mipmap.btn_homeasup_default);
            list = manager.meals("table5");
            adapter = new ServerAdapter(this, list);
            ServerListView.setAdapter(adapter);
        } else if (index == 6) {
            setActionBar("银行证劵", false, R.mipmap.btn_homeasup_default);
            list = manager.meals("table6");
            adapter = new ServerAdapter(this, list);
            ServerListView.setAdapter(adapter);
        } else if (index == 7) {
            setActionBar("保险服务", false, R.mipmap.btn_homeasup_default);
            list = manager.meals("table7");
            adapter = new ServerAdapter(this, list);
            ServerListView.setAdapter(adapter);
        } else if (index == 8) {
            setActionBar("售后服务", false, R.mipmap.btn_homeasup_default);
            list = manager.meals("table8");
            adapter = new ServerAdapter(this, list);
            ServerListView.setAdapter(adapter);
        }
        title_leftbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.filetype_in_anim, R.anim.filetype_out_anim);
            }
        });
        ServerListView.setOnItemClickListener(this);
    }

    Intent intent;

    public void call(int position) {
        intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + list.get(position).getNumber()));
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        call(position);
    }
}
