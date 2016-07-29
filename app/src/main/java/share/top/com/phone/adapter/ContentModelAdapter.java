package share.top.com.phone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import share.top.com.phone.R;
import share.top.com.phone.beans.ContentModel;

/**
 * Created by ZHOU on 2016/2/25.
 */
public class ContentModelAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<ContentModel> list;

    public ContentModelAdapter(Context mContext, List<ContentModel> list) {
        this.mContext = mContext;
        this.list = list;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return  list.size();
    }

    @Override
    public Object getItem(int position) {
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        ImageView item_imageview;
        TextView item_textview;
    }

    ViewHolder holder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.content_item, null);
            holder = new ViewHolder();
            holder.item_imageview = (ImageView) convertView.findViewById(R.id.item_imageview);
            holder.item_textview = (TextView) convertView.findViewById(R.id.item_textview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.item_textview.setText(list.get(position).getText());
        holder.item_imageview.setImageResource(list.get(position).getImage());
        return convertView;
    }
}
