package share.top.com.phone.beans;

import android.graphics.drawable.Drawable;

/**
 * Created by ZHOU on 2016/3/10.
 */
public class ClearAppBean {
    private String softChinesename;
    private String softEnglishname;
    private String apkname;
    private String filepath;
    private boolean isClean;
    private Drawable softIcon;
    private long size;

    public ClearAppBean(String softChinesename, String softEnglishname, String apkname, String filepath) {
        this.softChinesename = softChinesename;
        this.softEnglishname = softEnglishname;
        this.apkname = apkname;
        this.filepath = filepath;
        isClean = true;
    }

    public String getSoftChinesename() {
        return softChinesename;
    }

    public void setSoftChinesename(String softChinesename) {
        this.softChinesename = softChinesename;
    }

    public String getSoftEnglishname() {
        return softEnglishname;
    }

    public void setSoftEnglishname(String softEnglishname) {
        this.softEnglishname = softEnglishname;
    }

    public String getApkname() {
        return apkname;
    }

    public void setApkname(String apkname) {
        this.apkname = apkname;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public boolean isClean() {
        return isClean;
    }

    public void setClean(boolean isClean) {
        this.isClean = isClean;
    }

    public Drawable getSoftIcon() {
        return softIcon;
    }

    public void setSoftIcon(Drawable softIcon) {
        this.softIcon = softIcon;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

}

