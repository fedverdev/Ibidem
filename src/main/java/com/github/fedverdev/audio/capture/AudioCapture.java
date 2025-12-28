package com.github.fedverdev.audio.capture;

import javax.sound.sampled.LineUnavailableException;

public interface AudioCapture {
    public void startCapture() throws LineUnavailableException;

    public void stopCapture();

    public void captureTo(byte[] buffer);
}
