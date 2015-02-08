package com.sting2me.common.entity;


/**
 * Created by peter on 14-12-5.
 */
public class ImageRequest extends MultimediaRequest {
    private int width;
    private int height;
    private int dpi;
    // place width and height in file name
    private boolean extendName;
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
	public int getDpi() {
		return dpi;
	}
	public void setDpi(int dpi) {
		this.dpi = dpi;
	}
	public boolean isExtendName() {
		return extendName;
	}
	public void setExtendName(boolean extendName) {
		this.extendName = extendName;
	}
    
}
