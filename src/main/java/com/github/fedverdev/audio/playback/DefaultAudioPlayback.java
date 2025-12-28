package com.github.fedverdev.audio.playback;

import com.github.fedverdev.audio.AudioConfig;

import javax.sound.sampled.*;

public class DefaultAudioPlayback implements AudioPlayback {

    private final SourceDataLine sourceDataLine;

    public DefaultAudioPlayback(SourceDataLine sourceDataLine) throws LineUnavailableException {
        this.sourceDataLine = sourceDataLine;
    }
    @Override
    public void startPlayback() throws LineUnavailableException {
        sourceDataLine.open();
        sourceDataLine.start();
    }

    @Override
    public void stopPlayback() {
        sourceDataLine.stop();
        sourceDataLine.close();
    }

    @Override
    public void playbackFrom(byte[] buffer) {
        sourceDataLine.write(buffer, 0, buffer.length);
    }
}
