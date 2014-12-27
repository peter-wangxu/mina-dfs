package com.sting2me.common.entity;

/**
 * Created by peter on 14-12-5.
 */
public enum FileType {
    ImageFile("Image"),
    AudioFile("Audio"),
    VideoFile("Video"),
    TextFile("Text"),
    Any("Any");

    private FileType(String typeName) {
        this.typeName = typeName;
    }
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
