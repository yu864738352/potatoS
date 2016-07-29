package share.top.com.phone.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Debug;

import java.util.ArrayList;
import java.util.List;

import share.top.com.phone.beans.AppInfo;

/**
 * Created by ZHOU on 2016/3/1.
 */
public class RunAppUtil {

    //该类用于正在运行程序进行管理  查询以及卸载
    private static RunAppUtil util;
    private ActivityManager aManager;
    private PackageManager pManager;

    private RunAppUtil(Context mContext) {
        aManager = (ActivityManager) mContext.getSystemService(Activity.ACTIVITY_SERVICE);
        pManager = mContext.getPackageManager();
    }

    public static RunAppUtil getInstance(Context mContext) {
        if (util == null) {
            util = new RunAppUtil(mContext);
        }
        return util;
    }

    private ArrayList<AppInfo> All_List = new ArrayList<>();
    private ArrayList<AppInfo> System_List = new ArrayList<>();
    private ArrayList<AppInfo> User_List = new ArrayList<>();

    public ArrayList<AppInfo> getAll_List() {
        if (All_List == null || All_List.size() == 0) {
            getRunApp();
        }
        return All_List;
    }

    public ArrayList<AppInfo> getSystem_List() {
        if (All_List == null || All_List.size() == 0) {
            getRunApp();
        }
        return System_List;
    }

    public ArrayList<AppInfo> getUser_List() {
        if (All_List == null || All_List.size() == 0) {
            getRunApp();
        }
        return User_List;
    }

    public void getRunApp() {
        All_List.clear();
        System_List.clear();
        User_List.clear();
        List<ActivityManager.RunningAppProcessInfo> list = aManager.getRunningAppProcesses();
        Drawable drawable;
        String appName;
        String appSize;
        for (ActivityManager.RunningAppProcessInfo info : list) {
            try {
                drawable = pManager.getApplicationBanner(info.processName);
                Debug.MemoryInfo[] memoryInfos = aManager.getProcessMemoryInfo(new int[]{info.pid});
                appSize = memoryInfos[0].getTotalPrivateDirty() + "";
                ApplicationInfo infos = pManager.getApplicationInfo(info.processName, 0);//通过名字获取infos对象
                appName = pManager.getApplicationLabel(infos) + "";//通过获取infos对象获取应用名字
                //生成对象
                String PackgeName = info.processName;
                AppInfo appInfo = new AppInfo(appName, appSize, drawable, PackgeName, info.importance);
                if ((infos.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {//ApplicationInfo.FLAG_SYSTEM=1
                    //系统程序
                    appInfo.setType(1);
                    System_List.add(appInfo);
                } else {
                    //用户程序
                    appInfo.setType(0);
                    User_List.add(appInfo);
                }
                All_List.add(appInfo);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 一键清理
     **/
    public void killAllBackGroundApp() {
//        for (int i = 0; i < All_List.size(); i++) {
//            if (All_List.get(i).getImportance() > ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
//                killBackGroundApp(All_List.get(i).getPackgeName());
//            }
//        }
        List<ActivityManager.RunningAppProcessInfo> list = aManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : list) {
            if (info.importance > ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE)
                aManager.killBackgroundProcesses(info.processName);
        }
    }

    long size = 0;
    long sum;

    public long getBackGroundSize() {
        String appSize;
        List<ActivityManager.RunningAppProcessInfo> list = aManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo infos : list) {
            Debug.MemoryInfo[] memoryInfos = aManager.getProcessMemoryInfo(new int[]{infos.pid});
            appSize = memoryInfos[0].getTotalPrivateDirty() + "";
            size = Long.parseLong(appSize);
            sum += size;
        }
        return sum;
    }

    public void killBackGroundApp(String packeName) {
        aManager.killBackgroundProcesses(packeName);
    }
}
