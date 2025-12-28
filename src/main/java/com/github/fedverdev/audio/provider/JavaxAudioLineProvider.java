package com.github.fedverdev.audio.provider;

import javax.sound.sampled.*;

public class JavaxAudioLineProvider implements AudioLineProvider {
    @Override
    public boolean isLineSupported(DataLine.Info info) {
        return AudioSystem.isLineSupported(info);
    }

    @Override
    public TargetDataLine getTargetLine(DataLine.Info info) throws LineUnavailableException {
        return (TargetDataLine) AudioSystem.getLine(info);
    }

    @Override
    public SourceDataLine getSourceLine(DataLine.Info info) throws LineUnavailableException {
        return (SourceDataLine) AudioSystem.getLine(info);
    }
}
