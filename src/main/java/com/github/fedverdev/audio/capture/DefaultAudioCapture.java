package com.github.fedverdev.audio.capture;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class DefaultAudioCapture implements AudioCapture {
    private final TargetDataLine targetDataLine;

    public DefaultAudioCapture(TargetDataLine targetDataLine) {
        this.targetDataLine = targetDataLine;
    }

    @Override
    public void startCapture() throws LineUnavailableException {
        targetDataLine.open();
        targetDataLine.start();
    }

    @Override
    public void stopCapture(){
        targetDataLine.stop();
        targetDataLine.close();
    }

    @Override
    public void captureTo(byte[] data) {
        targetDataLine.read(data, 0, data.length);
    }
}
