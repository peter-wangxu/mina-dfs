package com.sting2me.common.entity;

/**
 * Created by peter on 14-12-6.
 */
public class AudioRequest extends MultimediaRequest{
    //audio length
    private double audioLength;
    // place audioLength in file name
    private boolean extendName;
    // Bit per second
    private int bitPerSec;

    public double getAudioLength() {
        return audioLength;
    }

    public void setAudioLength(double audioLength) {
        this.audioLength = audioLength;
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
