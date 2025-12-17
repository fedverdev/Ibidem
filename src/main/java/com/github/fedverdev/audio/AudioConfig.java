package com.github.fedverdev.audio;

import javax.sound.sampled.AudioFormat;

public final class AudioConfig {
    public static final float SAMPLE_RATE = 44100.0f;
    public static final int SAMPLE_SIZE_BITS = 16;
    public static final int CHANNELS = 2;
    public static final boolean SIGNED = true;
    public static final boolean LITTLE_ENDIAN = false;
    public static final AudioFormat FORMAT = new AudioFormat(
            SAMPLE_RATE,
            SAMPLE_SIZE_BITS,
            CHANNELS,
            SIGNED,
            LITTLE_ENDIAN
    );

    private AudioConfig() {}
}
