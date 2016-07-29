package share.top.com.phone.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import share.top.com.phone.beans.AppInstall;

/**
 * Created by ZHOU on 2016/3/2.
 */
public class FileManager {

    private static FileManager manager;
    private PackageManager mPackageManager;
    private ArrayList<AppInstall> All_List = new ArrayList<>();
    private ArrayList<AppInstall> System_List = new ArrayList<>();
    private ArrayList<AppInstall> User_List = new ArrayList<>();

    private FileManager(Context mContext) {
        mPackageManager = mContext.getPackageManager();
    }

    public ArrayList<AppInstall> getAll_List() {
        return All_List;
    }

    public void setAll_List(ArrayList<AppInstall> all_List) {
        All_List = all_List;
    }

    public ArrayList<AppInstall> getUser_List() {
        return User_List;
    }

    public void setUser_List(ArrayList<AppInstall> user_List) {
        User_List = user_List;
    }

    public ArrayList<AppInstall> getSystem_List() {
        return System_List;
    }

    public void setSystem_List(ArrayList<AppInstall> system_List) {
        System_List = system_List;
    }

    public static FileManager getInstace(Context mContext) {
        if (manager == null) {
            manager = new FileManager(mContext);
        }

        return manager;
    }

    public void getInstallApp() {
        List<PackageInfo> infos = mPackageManager.
                getInstalledPackages(PackageManager.GET_ACTIVITIES | PackageManager.GET_UNINSTALLED_PACKAGES);
        for (PackageInfo packageInfo : infos) {
            //已经安装程序的集合
            String packageName = packageInfo.packageName;
            String versionName = packageInfo.versionName;
            String name = packageInfo.applicationInfo.loadLabel(mPackageManager).toString();
            Drawable iocn = packageInfo.applicationInfo.loadIcon(mPackageManager);
            AppInstall mInstall = new AppInstall(name, iocn, versionName, packageName);
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                mInstall.setType(0);
                User_List.add(mInstall);
            } else {
                mInstall.setType(1);
                System_List.add(mInstall);
            }
            All_List.add(mInstall);
        }
    }
}
