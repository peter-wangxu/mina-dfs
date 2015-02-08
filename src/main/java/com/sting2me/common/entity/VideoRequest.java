package com.sting2me.common.entity;

/**
 * Created by peter on 14-12-6.
 */
public class VideoRequest extends MultimediaRequest {
    private int width;
    private int height;
    // place audioLength in file name
    private boolean extendName;
    // Bit per second
    private int bitPerSec;
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public boolean isExtendName() {
		return extendName;
	}
	public void setExtendName(boolean extendName) {
		this.extendName = extendName;
	}
	public int getBitPerSec() {
		return bitPerSec;
	}
	public void setBitPerSec(int bitPerSec) {
		this.bitPerSec = bitPerSec;
	}
    
}
