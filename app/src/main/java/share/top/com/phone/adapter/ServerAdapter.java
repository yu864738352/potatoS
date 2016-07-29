package share.top.com.phone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import share.top.com.phone.R;
import share.top.com.phone.beans.OperatorBean;

/**
 * Created by ZHOU on 2016/3/8.
 */
public class ServerAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<OperatorBean> list;
    private LayoutInflater mInflater;

    public ServerAdapter(Context mContext, ArrayList<OperatorBean> list) {
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

    class ViewHodler {
        TextView ServerName, ServerNumber;
    }

    ViewHodler hodler;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.server_item_layout, null);
            hodler = new ViewHodler();
            hodler.ServerName = (TextView) convertView.findViewById(R.id.ServerName);
            hodler.ServerNumber = (TextView) convertView.findViewById(R.id.ServerNumber);
            convertView.setTag(hodler);
        } else {
            hodler = (ViewHodler) convertView.getTag();
        }
        hodler.ServerName.setText(list.get(position).getName());
        hodler.ServerNumber.setText(list.get(position).getNumber());
        return convertView;
    }
}
