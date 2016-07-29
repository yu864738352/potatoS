package share.top.com.phone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import share.top.com.phone.R;
import share.top.com.phone.beans.Persion;

/**
 * Created by ZHOU on 2016/3/9.
 */
public class PersionNumberAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Persion> list;
    private LayoutInflater mInflater;

    public PersionNumberAdapter(Context mContext, ArrayList<Persion> list) {
        this.mContext = mContext;
        this.list = list;
        mInflater = LayoutInflater.from(mContext);
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
        TextView PersionName, PersionNumber;
    }

    ViewHolder holder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.persion_item_layout, null);
            holder = new ViewHolder();
            holder.PersionName = (TextView) convertView.findViewById(R.id.PersionName);
            holder.PersionNumber = (TextView) convertView.findViewById(R.id.PersionNumber);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.PersionNumber.setText(list.get(position).getNumber());
        holder.PersionName.setText(list.get(position).getName());
        return convertView;
    }
}
