package share.top.com.phone.broad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import share.top.com.phone.MainActivity;

/**
 * Created by ZHOU on 2016/3/3.
 */
public class MyBroadcastReceiverSend extends BroadcastReceiver {
    /***
     * @param context 接受分为：
     *                动态接受（某个activity去注册广播，Activity关闭之前解除注册，，不然报错）
     * @param intent  静态注册：
     *                只要收到广播就能调用回调方法 在清单文件中进行注册
     */

    @Override
/**收到广播就会调用该方法*/
    public void onReceive(Context context, Intent intent) {
        String str = intent.getStringExtra("name");
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        //当收到广播之后将接受的app跳到MainActivity中去
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//如果发现对方不存在，就在栈中给他创建一个
        intent.setClass(context, MainActivity.class);
        context.startActivity(intent);
//判断是否是电池电量发生改变
        if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
            //获取电量信息
            //拿到总电量
            int all = intent.getIntExtra("scale", 0);
            //拥有的电量
            int level = intent.getIntExtra("level", 0);
            int port = (level * 100) / all;//百分比
            Log.i("port", port + "");
        }
    }
}
