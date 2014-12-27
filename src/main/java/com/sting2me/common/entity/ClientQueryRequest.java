package com.sting2me.common.entity;

/**
 * Created by peter on 14-12-5.
 * This class define the structure of request
 * from client to name server
 */
public class ClientQueryRequest {
    private FileType fileType;

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }
}
