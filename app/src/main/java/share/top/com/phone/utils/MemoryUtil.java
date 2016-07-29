package share.top.com.phone.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.DecimalFormat;

/**
 * 本类用于获取手机的内存信息，路径 大小
 * Created by ZHOU on 2016/2/23.
 */
public class MemoryUtil {

    private static MemoryUtil util;

    private MemoryUtil() {

    }

    public static MemoryUtil getInstance() {
        if (util == null) {
            util = new MemoryUtil();
        }
        return util;
    }

    /**
     * 判断内置sd卡是否存在
     */
    public boolean isInnerSdCard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    /**
     * 获取内置sd卡的路径
     *
     * @return
     */
    public File getInnerSdCard() {
        if (isInnerSdCard()) {
            return Environment.getExternalStorageDirectory();
        }
        return null;
    }

    /**
     * 获取内存卡的大小 单位是B
     */
    public long getInnerSdCardSize() {
        if (isInnerSdCard()) {
            StatFs statFs = new StatFs(getInnerSdCard().getPath());//获取测量的路径
            int count = statFs.getBlockCount();//多少个文件空间
            int size = statFs.getBlockSize();//获取每个文件的大小
            return count * size;
        }
        return 0;
    }

    /**
     * 获取Ram运行的内存的总大小,安卓中有一个文件记录了内存的大小、/proc/meminfo
     */
    public int[] getRamTotalSize() {
        int[] ram_info = new int[3];
        BufferedReader bufferedReader;
        FileReader reader;
        String temp;
        try {
            File file = new File("/proc/meminfo");
            reader = new FileReader(file);
            bufferedReader = new BufferedReader(reader);
            temp = bufferedReader.readLine();
            String split[] = temp.split("\\s+");
            ram_info[0] = Integer.valueOf(split[1]);
            temp = bufferedReader.readLine();
            split = temp.split("\\s+");
            ram_info[1] = Integer.valueOf(split[1]);
            ram_info[2] = ram_info[0] - ram_info[1];
            reader.close();
            bufferedReader.close();
            return ram_info;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private long getSDTotalSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return blockSize * totalBlocks;
    }

    /**
     * 获得机身内存总大小
     *
     * @return
     */
    public long getRomTotalSize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return blockSize * totalBlocks;
    }

    /**
     * 获得机身可用内存
     *
     * @return
     */
    public long getRomAvailableSize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return blockSize * availableBlocks;
    }

}
