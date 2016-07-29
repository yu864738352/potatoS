package share.top.com.phone.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by ZHOU on 2016/3/3.
 */
public class UnIntasllApp {

    private Context mContext;

    public UnIntasllApp(Context mContext) {
        this.mContext = mContext;
    }

    public void UnIntasll(String packageName) {
        Uri uri = Uri.parse("package:" + packageName);
        Intent intent = new Intent(Intent.ACTION_DELETE, uri);
        mContext.startActivity(intent);
    }
}
