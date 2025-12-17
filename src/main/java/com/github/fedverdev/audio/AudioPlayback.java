package com.github.fedverdev.audio;

import javax.sound.sampled.*;

public class AudioPlayback {

    private final SourceDataLine sourceDataLine;

    public AudioPlayback() throws LineUnavailableException {
        DataLine.Info sourceDLI = new DataLine.Info(SourceDataLine.class, AudioConfig.FORMAT);
        if (!AudioSystem.isLineSupported(sourceDLI)) {
            System.err.println("Line not supported");
            System.exit(0);
        }

        sourceDataLine = (SourceDataLine) AudioSystem.getLine(sourceDLI);
        sourceDataLine.open();
        sourceDataLine.start();
    }

    public void playFrom(byte[] data) {
        sourceDataLine.write(data, 0, data.length);
    }
}
