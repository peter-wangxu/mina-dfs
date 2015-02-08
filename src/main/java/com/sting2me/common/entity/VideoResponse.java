package com.sting2me.common.entity;

/**
 * Created by peter on 14-12-6.
 */
public class VideoResponse {
    //may contain http:// ftp:// prefix
    private String fullPath;

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
    
}
