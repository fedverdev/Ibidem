package com.github.fedverdev.audio.playback;

import com.github.fedverdev.audio.AudioConfig;
import com.github.fedverdev.audio.enums.AudioType;
import com.github.fedverdev.audio.provider.AudioLineProvider;
import lombok.AllArgsConstructor;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

@AllArgsConstructor
public class AudioPlaybackFactory {
    private AudioLineProvider provider;

    public AudioPlayback create(AudioType type) throws LineUnavailableException {
        switch (type) {
            case DEFAULT_TYPE:
                return new DefaultAudioPlayback(getDefaultSourceDataLine());
            default:
                throw new IllegalArgumentException(String.format("type [%s] is not supported", type));
        }
    }

    public SourceDataLine getDefaultSourceDataLine() throws LineUnavailableException {
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, AudioConfig.FORMAT);
        if (!provider.isLineSupported(info)) {
            throw new LineUnavailableException();
        }

        return (SourceDataLine) AudioSystem.getLine(info);
    }
}
