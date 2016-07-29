package share.top.com.phone.adapter;

import android.content.Context;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import share.top.com.phone.R;
import share.top.com.phone.beans.ClearAppBean;

/**
 * Created by ZHOU on 2016/3/10.
 */
public class PhoneCleanAdapter extends BaseAdapter {

    private ArrayList<ClearAppBean> list;
    private Context context;

    public ArrayList<ClearAppBean> getList() {
        return list;
    }

    public void setList(ArrayList<ClearAppBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public PhoneCleanAdapter(Context context) {
        list = new ArrayList<ClearAppBean>();
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PhoneCleanViewHolder holder;
        if (convertView == null) {
            holder = new PhoneCleanViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.phone_clean_item, null);
            holder.cbBox = (CheckBox) convertView
                    .findViewById(R.id.phone_clean_item_cb);
            holder.appname = (TextView) convertView
                    .findViewById(R.id.phone_clean_item_appname);
            holder.appsize = (TextView) convertView
                    .findViewById(R.id.phone_clean_item_appsize);
            holder.iv = (ImageView) convertView
                    .findViewById(R.id.phone_clean_item_icon);
            convertView.setTag(holder);
        } else {
            holder = (PhoneCleanViewHolder) convertView.getTag();
        }
        final ClearAppBean info = list.get(position);
        holder.appname.setText(info.getSoftChinesename());
        holder.iv.setImageDrawable(info.getSoftIcon());
        holder.appsize.setText(Formatter.formatFileSize(context, info.getSize()) + "");
        holder.cbBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                info.setClean(isChecked);
            }
        });
        holder.cbBox.setChecked(info.isClean());
        return convertView;
    }

    private class PhoneCleanViewHolder {
        CheckBox cbBox;
        TextView appname;
        ImageView iv;
        TextView appsize;
    }
}
