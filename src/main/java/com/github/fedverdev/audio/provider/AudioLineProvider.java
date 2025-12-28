package com.github.fedverdev.audio.provider;

import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public interface AudioLineProvider {
    boolean isLineSupported(DataLine.Info info);
    TargetDataLine getTargetLine(DataLine.Info info) throws LineUnavailableException;
    SourceDataLine getSourceLine(DataLine.Info info) throws LineUnavailableException;
}
