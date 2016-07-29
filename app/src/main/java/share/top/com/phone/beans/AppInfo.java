package share.top.com.phone.beans;

import android.graphics.drawable.Drawable;

/**
 * Created by ZHOU on 2016/2/29.
 */
public class AppInfo {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    //图标,名字，大小，类型0代表系统，1代表用户程序
    private String name;
    private String size;
    private int type;
    private String PackgeName;
    private int importance;

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public boolean isCheck_flag() {
        return check_flag;
    }

    public void setCheck_flag(boolean check_flag) {
        this.check_flag = check_flag;
    }

    public String getPackgeName() {
        return PackgeName;
    }

    public void setPackgeName(String packgeName) {
        PackgeName = packgeName;
    }

    private Drawable icon;
    private boolean check_flag;

    public AppInfo(String name, String size, Drawable icon, String PackgeName, int importance) {
        this.name = name;
        this.size = size;
        this.icon = icon;
        this.PackgeName = PackgeName;
        this.importance = importance;
    }
}
