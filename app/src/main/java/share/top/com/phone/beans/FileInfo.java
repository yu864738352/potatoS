package share.top.com.phone.beans;

import java.io.File;
import java.io.Serializable;

/**
 * Created by ZHOU on 2016/2/23.
 */
public class FileInfo implements Serializable {

    /**
     * 该类用于文件信息的保存
     * 内容：文件File类，Type类型,icon图标
     */
    private File file;
    private String Type;
    private int icon;
    private boolean check;
    private static FileInfo info;
    private FileInfo() {

    }
   public static  FileInfo getInstance(){
       if (info==null ){
           info=new FileInfo();
       }
       return info;
   }
    public boolean isDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    private boolean isDelete;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    private double MUSIC_size;
    private double VIOD_size;
    private double ZIP_size;
    private double APK_size;
    private double ALL_size;
    private double IMG_size;
    private double TXT_size;

    public double getMUSIC_size() {
        return MUSIC_size;
    }

    public void setMUSIC_size(double MUSIC_size) {
        this.MUSIC_size = MUSIC_size;
    }

    public double getVIOD_size() {
        return VIOD_size;
    }

    public void setVIOD_size(double VIOD_size) {
        this.VIOD_size = VIOD_size;
    }

    public double getZIP_size() {
        return ZIP_size;
    }

    public void setZIP_size(double ZIP_size) {
        this.ZIP_size = ZIP_size;
    }

    public double getAPK_size() {
        return APK_size;
    }

    public void setAPK_size(double APK_size) {
        this.APK_size = APK_size;
    }

    public double getALL_size() {
        return ALL_size;
    }

    public void setALL_size(double ALL_size) {
        this.ALL_size = ALL_size;
    }

    public double getIMG_size() {
        return IMG_size;
    }

    public void setIMG_size(double IMG_size) {
        this.IMG_size = IMG_size;
    }

    public double getTXT_size() {
        return TXT_size;
    }

    public void setTXT_size(double TXT_size) {
        this.TXT_size = TXT_size;
    }


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public FileInfo(String type, File file, int icon, boolean isDelete ) {
        Type = type;
        this.file = file;
        this.icon = icon;
        this.isDelete = isDelete;
    }
}
