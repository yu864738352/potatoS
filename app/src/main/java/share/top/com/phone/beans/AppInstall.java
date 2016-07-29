package share.top.com.phone.beans;

import android.graphics.drawable.Drawable;

/**
 * Created by ZHOU on 2016/3/2.
 */
public class AppInstall {

    private String AppName;
    private Drawable AppDrawable;
    private String AppVersionName;
    private String AppPackageName;
    private boolean check_flag;

    public boolean isCheck_flag() {
        return check_flag;
    }

    public void setCheck_flag(boolean check_flag) {
        this.check_flag = check_flag;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int type;

    public String getAppName() {
        return AppName;
    }

    public AppInstall(String appName, Drawable appDrawable, String appVersionName, String appPackageName) {
        AppName = appName;
        AppDrawable = appDrawable;
        AppVersionName = appVersionName;
        AppPackageName = appPackageName;
    }

    public void setAppName(String appName) {
        AppName = appName;
    }

    public Drawable getAppDrawable() {
        return AppDrawable;
    }

    public void setAppDrawable(Drawable appDrawable) {
        AppDrawable = appDrawable;
    }

    public String getAppVersionName() {
        return AppVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        AppVersionName = appVersionName;
    }

    public String getAppPackageName() {
        return AppPackageName;
    }

    public void setAppPackageName(String appPackageName) {
        AppPackageName = appPackageName;
    }
}
