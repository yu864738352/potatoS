package share.top.com.phone.broad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;

import share.top.com.phone.adapter.ManagerSoftAdapter;
import share.top.com.phone.backinterface.AdapterInterface;
import share.top.com.phone.beans.AppInstall;

/**
 * Created by ZHOU on 2016/3/3.
 */
public class AdapterBroadcast extends BroadcastReceiver {
    public static boolean isShow;

    @Override
    public void onReceive(Context context, Intent intent) {
        //接收广播：设备上删除了一个应用程序包。
            isShow = true;
            Log.e("tag","1312313");
    }

}
