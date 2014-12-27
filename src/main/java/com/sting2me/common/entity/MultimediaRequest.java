package com.sting2me.common.entity;

/**
 * Created by peter on 14-12-6.
 */
public class MultimediaRequest {
    protected byte[] fileData;
    protected String fileExtension;

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
}

