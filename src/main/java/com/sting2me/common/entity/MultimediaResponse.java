package com.sting2me.common.entity;

/**
 * Created by peter on 14-12-7.
 */
public class MultimediaResponse {
    //may contain http:// ftp:// prefix
    protected String fullPath;

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }
}
