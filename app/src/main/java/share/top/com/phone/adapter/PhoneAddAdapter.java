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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import share.top.com.phone.R;
import share.top.com.phone.beans.AppInfo;

/**
 * Created by ZHOU on 2016/2/29.
 */
public class PhoneAddAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<AppInfo> list;
    private LayoutInflater mInflater;

    public PhoneAddAdapter(Context mContext, ArrayList<AppInfo> list) {
        this.mContext = mContext;
        this.list = list;
        mInflater = LayoutInflater.from(mContext);
    }

    public void UpdateWork(ArrayList<AppInfo> list) {
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
        private TextView add_Name, add_size;
        private CheckBox add_box;
        private ImageView add_image;
    }

    ViewHolder holder;
    int ss = 0;

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.add_adapter_layout, null);
            holder = new ViewHolder();
            holder.add_box = (CheckBox) convertView.findViewById(R.id.add_box);
            holder.add_image = (ImageView) convertView.findViewById(R.id.add_image);
            holder.add_Name = (TextView) convertView.findViewById(R.id.add_Name);
            holder.add_size = (TextView) convertView.findViewById(R.id.add_size);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        double dos = Double.valueOf(list.get(position).getSize());
        holder.add_size.setText(unit(dos));
        holder.add_Name.setText(list.get(position).getName());
        holder.add_image.setBackground(list.get(position).getIcon());
        holder.add_box.setTag(position);//设置标签这句话一定要写在获取值之前
        holder.add_box.setChecked(list.get(position).isCheck_flag());
        holder.add_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ss = (Integer) holder.add_box.getTag();
                list.get(ss).setCheck_flag(isChecked);
            }
        });
        return convertView;
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

}
