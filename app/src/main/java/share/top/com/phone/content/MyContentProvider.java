package share.top.com.phone.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import share.top.com.phone.db.DBManager;

/**
 * Created by ZHOU on 2016/3/9. 改程序作为提供者
 * <p>
 * 作为类容提供者必须要有这六个方法 给获取者调用
 */
public class MyContentProvider extends ContentProvider {
    /***
     * 创建方法
     */
    DBManager db;

    @Override
    public boolean onCreate() {
        db = DBManager.getInstance(getContext());
        return false;
    }

    /***
     * 查询方法
     ***/
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.i("msg", "调用了查询方法");
        db.meals("table1");
        return null;
    }

    /**
     * 获取类型
     **/
    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    /**
     * 添加数据方法
     */
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String name = (String) values.get("name");
        String number = (String) values.get("number");
        Log.i("msg", number + ":" + name);
        return null;
    }

    /***
     * 删除给外部的获取者使用
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    /***
     * 更新方法
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
