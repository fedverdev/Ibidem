package com.github.fedverdev.audio;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class AudioCapture {
    private final TargetDataLine targetDataLine;

    public AudioCapture() throws LineUnavailableException {
        DataLine.Info targetDLI = new DataLine.Info(TargetDataLine.class, AudioConfig.FORMAT);
        if (!AudioSystem.isLineSupported(targetDLI)) {
            System.err.println("Line not supported");
            System.exit(0);
        }

        targetDataLine = (TargetDataLine) AudioSystem.getLine(targetDLI);
        targetDataLine.open();
        targetDataLine.start();
    }

    public void captureTo(byte[] data) {
        targetDataLine.read(data, 0, data.length);
    }
}
