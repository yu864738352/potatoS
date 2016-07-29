package share.top.com.phone.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import share.top.com.phone.R;
import share.top.com.phone.beans.FileInfo;

/**
 * Created by ZHOU on 2016/2/24.
 */
public class FileInfoAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<FileInfo> list;
    private LayoutInflater mInflater;
    // 用来控制CheckBox的选中状况
    public HashMap<Integer, Boolean> isSelected;
    private int drawable;

    public FileInfoAdapter(Context mContext, ArrayList<FileInfo> list, int drawable) {
        this.mContext = mContext;
        this.list = list;
        mInflater = LayoutInflater.from(mContext);
        this.drawable = drawable;
    }

    public FileInfoAdapter(Context mContext, ArrayList<FileInfo> list, HashMap<Integer, Boolean> isSelected) {
        this.mContext = mContext;
        this.list = list;
        mInflater = LayoutInflater.from(mContext);
        this.isSelected = isSelected;
        initDate();
    }

    public void UpdateAdapter(ArrayList<FileInfo> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        if (list == null) {
            return position;
        }
        return list.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHodler {
        TextView FileInfoMusicName;
        ImageView FileInfoImageView;
        CheckBox FileInfoCheck;
    }

    ViewHodler hodler;

    private void initDate() {
        for (int i = 0; i < list.size(); i++) {
            isSelected.put(i, false);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.fileinfo_adapter_item, null);
            hodler = new ViewHodler();
            hodler.FileInfoMusicName = (TextView) convertView.findViewById(R.id.FileInfoMusicName);
            hodler.FileInfoImageView = (ImageView) convertView.findViewById(R.id.FileInfoImageView);
            hodler.FileInfoImageView.setImageResource(drawable);
//            hodler.FileInfoCheck = (CheckBox) convertView.findViewById(R.id.FileInfoCheck);
            convertView.setTag(hodler);
        } else
            hodler = (ViewHodler) convertView.getTag();
        hodler.FileInfoImageView.setImageResource(list.get(position).getIcon());
        hodler.FileInfoMusicName.setText(list.get(position).getFile().getName());
//        hodler.FileInfoCheck.setChecked(isSelected.get(position));
        return convertView;
    }
}
