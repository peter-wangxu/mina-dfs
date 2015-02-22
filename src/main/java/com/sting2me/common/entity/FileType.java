package com.sting2me.common.entity;

/**
 * Created by peter on 14-12-5.
 */
public enum FileType {
    IMAGE("Image"),
    AUDIO("Audio"),
    VIDEO("Video"),
    TEXT("Text"),
    RAW("Raw");
    private String typeName;

    private FileType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
