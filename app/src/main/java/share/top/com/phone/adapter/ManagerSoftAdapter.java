package share.top.com.phone.adapter;

import android.content.Context;
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
import share.top.com.phone.beans.AppInstall;

/**
 * Created by ZHOU on 2016/3/2.
 */
public class ManagerSoftAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<AppInstall> list;
    private LayoutInflater mInflater;

    public ManagerSoftAdapter(Context mContext, ArrayList<AppInstall> list) {
        this.mContext = mContext;
        this.list = list;
        mInflater = LayoutInflater.from(mContext);
    }

    public void UpdateWork(ArrayList<AppInstall> list) {
        this.list = list;
        notifyDataSetChanged();
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

    class ViewHolder {
        TextView manager_add_Name, manager_add_size;
        CheckBox manager_add_box;
        ImageView manager_add_image;
    }

    ViewHolder holder;
    int ss = 0;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.manager_layout, null);
            holder.manager_add_box = (CheckBox) convertView.findViewById(R.id.manager_add_box);
            holder.manager_add_image = (ImageView) convertView.findViewById(R.id.manager_add_image);
            holder.manager_add_Name = (TextView) convertView.findViewById(R.id.manager_add_Name);
            holder.manager_add_size = (TextView) convertView.findViewById(R.id.manager_add_size);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.manager_add_Name.setText(list.get(position).getAppName());
        holder.manager_add_size.setText(list.get(position).getAppVersionName());
        holder.manager_add_image.setImageDrawable(list.get(position).getAppDrawable());
        holder.manager_add_box.setTag(position);
        holder.manager_add_box.setChecked(list.get(position).isCheck_flag());
        holder.manager_add_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ss = (Integer) buttonView.getTag();
                list.get(ss).setCheck_flag(isChecked);
            }
        });
        return convertView;
    }
}
