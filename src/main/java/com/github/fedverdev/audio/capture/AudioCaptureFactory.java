package com.github.fedverdev.audio.capture;

import com.github.fedverdev.audio.AudioConfig;
import com.github.fedverdev.audio.provider.AudioLineProvider;
import lombok.AllArgsConstructor;

import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

@AllArgsConstructor
public class AudioCaptureFactory {
    private AudioLineProvider provider;

    public enum AudioCaptureType {
        DEFAULT_TYPE
    }

    public AudioCapture create(AudioCaptureType type) throws LineUnavailableException {
        switch (type) {
            case DEFAULT_TYPE:
                return new DefaultAudioCapture(createDefault());
            default:
                throw new IllegalArgumentException(String.format("type [%s] is not supported", type));
        }
    }

    private TargetDataLine createDefault() throws LineUnavailableException{
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, AudioConfig.FORMAT);
        if (!provider.isLineSupported(info)) {
            throw new LineUnavailableException();
        }

        return provider.getLine(info);
    }
}
