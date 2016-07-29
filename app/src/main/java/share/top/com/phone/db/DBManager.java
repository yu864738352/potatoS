package share.top.com.phone.db;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import share.top.com.phone.R;
import share.top.com.phone.beans.ClearAppBean;
import share.top.com.phone.beans.OperatorBean;

/**
 * Created by ZHOU on 2016/3/8.
 */
public class DBManager {

    private Context context;
    private static DBManager manager;
    private SQLiteDatabase db;
    private File file;

    private DBManager(Context mContext) {
        context = mContext;
        file = new File("data/data/share.top.com.phone/commonnum.db");
        db = SQLiteDatabase.openOrCreateDatabase(file, null);
    }

    public static DBManager getInstance(Context mContext) {
        if (manager == null) {
            manager = new DBManager(mContext);
        }
        return manager;
    }

    public void getFile(Context mContext) {
        InputStream input;
        FileOutputStream output;
        try {
            AssetManager set = mContext.getAssets();
            input = set.open("commonnum.db");
            output = new FileOutputStream(file);
            int index;
            while ((index = input.read()) != -1) {
                output.write(index);
            }
            input.close();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 订餐查询
     */
    public ArrayList<OperatorBean> meals(String table) {
        String sql = "select * from " + table;
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<OperatorBean> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String number = cursor.getString(cursor.getColumnIndex("number"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            list.add(new OperatorBean(id, name, number));
        }
        cursor.close();
        return list;
    }
/**---------------------------------------------------------------------------------------***/
    /**
     * 包名
     */
    private final String PACKAGE_NAME = "share.top.com.phone";
    /**
     * 数据库文件名
     */
    private final String FILE_NAME = "clearpath.db";
    /**
     * 文件路径
     */
    private final String FILE_PATH = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + PACKAGE_NAME;

    //    清理文件的bean、
    private ArrayList<ClearAppBean> list;

    /**
     * 将应用信息写入包中的方法
     */
    public void saveDate(Context context) {
        try {
            File file = new File(FILE_PATH + "/" + FILE_NAME);
            if (file.exists()) {
                return;
            }
            // 利用resources获取assets文件夹下源文件的文件的流
            InputStream is = context.getResources().getAssets()
                    .open("clearpath.db");
            FileOutputStream fos = new FileOutputStream(file);
            byte[] arr = new byte[1024];
            int len;
            while ((len = is.read(arr)) != -1) {
                fos.write(arr, 0, len);
                fos.flush();
            }
            fos.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有应用类型
     *
     * @return 类型集合
     */
    public ArrayList<ClearAppBean> readSoftdetailTable() {
        saveDate(context);
        list = new ArrayList<ClearAppBean>();
        // 开启数据库
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(FILE_PATH
                + "/" + FILE_NAME, null);
        String title = "select * from softdetail";
        // 获取游标
        Cursor cursor = database.rawQuery(title, null);
        // 当游标能往下读的时候
        while (cursor.moveToNext()) {
            // 从name字段中获取到名字
            String softChinesename = cursor.getString(cursor
                    .getColumnIndex("softChinesename"));
            // 从index字段中获取到角标
            String softEnglishname = cursor.getString(cursor
                    .getColumnIndex("softEnglishname"));
            String apkname = cursor.getString(cursor.getColumnIndex("apkname"));
            String filepath = Environment.getExternalStorageDirectory()
                    .getPath()
                    + cursor.getString(cursor.getColumnIndex("filepath"));
            ClearAppBean info = new ClearAppBean(softChinesename,
                    softEnglishname, apkname, filepath);
            // 向集合中添加name
            list.add(info);
        }
        return list;
    }

    /**
     * 获得手机已经安装的和数据库中对应的App的信息集合
     */
    public ArrayList<ClearAppBean> getPhoneRubbishfile(Context context) {
        if (list == null) {
            list = readSoftdetailTable();
        }
        ArrayList<ClearAppBean> phontSoftdetailInfos = new ArrayList<ClearAppBean>();
        for (ClearAppBean ClearAppBean : list) {
            File file = new File(ClearAppBean.getFilepath());
            if (file.exists() && file.length() > 0) {
                Drawable icon = null;
                try {
                    icon = context.getPackageManager().getApplicationIcon(ClearAppBean.getApkname());
                } catch (PackageManager.NameNotFoundException e) {
                    icon = context.getResources().getDrawable(R.mipmap.ic_launcher);
                }
                ClearAppBean.setSoftIcon(icon);
                phontSoftdetailInfos.add(ClearAppBean);
            }
        }
        return phontSoftdetailInfos;
    }
}
