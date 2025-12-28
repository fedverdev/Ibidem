package com.github.fedverdev.audio.playback;

import javax.sound.sampled.LineUnavailableException;

public interface AudioPlayback {
    void startPlayback() throws LineUnavailableException;
    void stopPlayback();
    void playbackFrom(byte[] buffer);
}
