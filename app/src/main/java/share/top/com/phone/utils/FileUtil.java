package share.top.com.phone.utils;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;

import share.top.com.phone.R;
import share.top.com.phone.beans.FileInfo;

/**
 * 遍历文件
 * Created by ZHOU on 2016/2/23. 单例模式
 */
public class FileUtil {

    private static FileUtil util;
    public boolean end_flag = false;// false代表正常执行，true代表终止

    public ArrayList<FileInfo> getALL_List() {
        return ALL_List;
    }

    public void setALL_List(ArrayList<FileInfo> ALL_List) {
        this.ALL_List = ALL_List;
    }

    private ArrayList<FileInfo> ALL_List = new ArrayList<>();
    public ArrayList<FileInfo> TXT_List = new ArrayList<>();
    public ArrayList<FileInfo> IMG_List = new ArrayList<>();
    public ArrayList<FileInfo> MUSIC_List = new ArrayList<>();
    public ArrayList<FileInfo> VIOD_List = new ArrayList<>();
    public ArrayList<FileInfo> ZIP_List = new ArrayList<>();
    public ArrayList<FileInfo> APK_List = new ArrayList<>();
    private double ALL_size;
    private double IMG_size;
    private double TXT_size;

    public double getALL_size() {
        return ALL_size;
    }

    public double getIMG_size() {
        return IMG_size;
    }

    public double getTXT_size() {
        return TXT_size;
    }

    public ArrayList<FileInfo> getTXT_List() {
        return TXT_List;
    }

    public ArrayList<FileInfo> getIMG_List() {
        return IMG_List;
    }

    public ArrayList<FileInfo> getMUSIC_List() {
        return MUSIC_List;
    }

    public ArrayList<FileInfo> getVIOD_List() {
        return VIOD_List;
    }

    public ArrayList<FileInfo> getZIP_List() {
        return ZIP_List;
    }

    public ArrayList<FileInfo> getAPK_List() {
        return APK_List;
    }

    public double getMUSIC_size() {
        return MUSIC_size;

    }

    public double getVIOD_size() {
        return VIOD_size;
    }

    public double getZIP_size() {
        return ZIP_size;
    }

    public double getAPK_size() {
        return APK_size;
    }

    private double MUSIC_size;
    private double VIOD_size;
    private double ZIP_size;
    private double APK_size;

    private FileUtil() {
    }

    public static FileUtil getIntance() {
        if (util == null)
            util = new FileUtil();
        return util;
    }

    /**
     * 提供外部的方法
     */
    private callBackInterface callBack;

    public void Work(File file, callBackInterface callBack) {
        this.callBack = callBack;
        End_Flag = false;//开启可递归
        i = 0;
        clean();
        OpenDir(file);
        callBack.endOpen(i);
    }

    private void clean() {
        ALL_size = 0;
        IMG_size = 0;
        TXT_size = 0;
        MUSIC_size = 0;
        VIOD_size = 0;
        ZIP_size = 0;
        APK_size = 0;
        ALL_List.clear();
        TXT_List.clear();
        IMG_List.clear();
        MUSIC_List.clear();
        VIOD_List.clear();
        ZIP_List.clear();
        APK_List.clear();
    }

    public boolean isEnd_Flag() {
        return End_Flag;
    }

    public void setEnd_Flag(boolean end_Flag) {
        End_Flag = end_Flag;
    }

    /**
     * 遍历文件夹递归函数
     */
    private boolean End_Flag;//定义终止阀值false 代表正常执行，true 代表不执行
    int i = 0;

    private void OpenDir(File file) {
        //添加一个终止阀值
        if (End_Flag) {//代表需要终止次次的遍历
            // callBack.endOpen(End_Flag);
            i = 1;
            return;
        }
        if (file == null || !file.exists() || file.length() <= 0 || !file.canRead())
            return;
        if (file != null && file.exists()) {
            if (file.isFile()) {
                //不是空文件，获取后缀名类型
                //保存在对应的集合里面
                saveFile(getFileType(file), file);
            } else {
                //是路径
                File files[] = file.listFiles();
                if (files == null || files.length == 0)
                    return;
                for (File f : files) {
                    OpenDir(f);
                }
            }
        }
    }

    /**
     * 判断后缀名类型的方法
     */
    static final String[][] TXT_TYPE =
            {{"文本", "txt"}, {"文本", "doc"},
                    {"文本", "docx"}, {"图片", "png"}, {"图片", "jpg"}, {"图片", "gif"}, {"图片", "bmp"},
                    {"视频", "mp4"}, {"视频", "avi"}, {"视频", "rmvb"}, {"视频", "mkv"}, {"视频", "rm"},
                    {"音乐", "mp3"}, {"音乐", "wav"}, {"压缩包", "zip"}, {"压缩包", "rar"}, {"应用包", "apk"}};

    /**
     * 获取文件的类型
     *
     * @param file
     */
    public String getFileType(File file) {
        for (int i = 0; i < TXT_TYPE.length; i++) {
            if (file.getName().toLowerCase().endsWith(TXT_TYPE[i][1])) {
                return TXT_TYPE[i][0];
            }
        }
        return "未知文件类型";
    }

    private void saveFile(String type, File file) {
        FileInfo info = null;
        if (type.equals("文本")) {
            info = new FileInfo(type, file, R.mipmap.icon_doc, false);
            TXT_List.add(info);
            TXT_size += file.length();
        } else if (type.equals("图片")) {
            info = new FileInfo(type, file, R.mipmap.icon_png, false);
            IMG_List.add(info);
            IMG_size += file.length();
        } else if (type.equals("视频")) {
            info = new FileInfo(type, file, R.mipmap.icon_video, false);
            VIOD_List.add(info);
            VIOD_size += file.length();
        } else if (type.equals("音乐")) {
            info = new FileInfo(type, file, R.mipmap.icon_wma, false);
            MUSIC_List.add(info);
            MUSIC_size += file.length();
        } else if (type.equals("压缩包")) {
            info = new FileInfo(type, file, R.mipmap.icon_rar, false);
            ZIP_List.add(info);
            ZIP_size += file.length();
        } else if (type.equals("应用包")) {
            info = new FileInfo(type, file, R.mipmap.icon_text_plain, false);
            APK_List.add(info);
            APK_size += file.length();
        }
        // info.setCheck(true);
        info = new FileInfo(type, file, R.mipmap.icon_file, false);
        ALL_List.add(info);
        Log.i("msg", "全部文件大小:" + ALL_List.size() + "");
        ALL_size += file.length();
        //调用回调方法
        callBack.ToUpdate(info);
    }

    public interface callBackInterface {
        void ToUpdate(FileInfo info);

        void endOpen(int i);
    }
    /**--------------------------------------------------*/
    /**
     * 返回文件大小
     */
    public static long getFileSize(File file) {
        long size = 0;
        if (!file.isDirectory()) {
            // 如果不是文件,则返回文件大小
            return file.length();
        }
        File files[] = file.listFiles(); // 如果是文件夹,则继续进行遍历.
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                size = size + getFileSize(files[i]);
            } else {
                size = size + files[i].length();
            }
        }
        return size;
    }

    /**
     * 删除文件的方法
     */
    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (int i = 0; i < files.length; ++i) {
                    deleteFile(files[i]);
                }
            }
        }
        file.delete();
    }
}



