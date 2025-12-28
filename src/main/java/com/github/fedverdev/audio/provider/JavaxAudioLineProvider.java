package com.github.fedverdev.audio.provider;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class JavaxAudioLineProvider implements AudioLineProvider {
    @Override
    public boolean isLineSupported(DataLine.Info info) {
        return AudioSystem.isLineSupported(info);
    }

    @Override
    public TargetDataLine getLine(DataLine.Info info) throws LineUnavailableException {
        return (TargetDataLine) AudioSystem.getLine(info);
    }
}
