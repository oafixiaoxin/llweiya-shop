package com.llweiya.ysx.starchef.business.user.model;

public class MediaBean {

    public MediaBean(String parentPath, String path, String thumbPath, int duration, long size, String displayName){
        this.path = path;
        this.thumbPath = thumbPath;
        this.duration = duration;
        this.size = size;
        this.displayName = displayName;
        this.parentPath = parentPath;
    }

    private String path = "";
    private String thumbPath = "";
    private int duration = 0;
    private long size = 0;
    private String displayName = "";
    private String parentPath = "";

    public String getPath() {
        return path;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public int getDuration() {
        return duration;
    }

    public long getSize() {
        return size;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getParentPath() {
        return parentPath;
    }
}
