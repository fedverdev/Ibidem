package com.github.fedverdev.audio.playback;

import com.github.fedverdev.audio.capture.DefaultAudioCapture;
import com.github.fedverdev.audio.enums.AudioType;
import com.github.fedverdev.audio.provider.AudioLineProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sound.sampled.SourceDataLine;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Audio playback test")
@ExtendWith(MockitoExtension.class)
public class AudioPlaybackTest {

    @Mock
    AudioLineProvider mockAudioLineProvider;

    @Mock
    SourceDataLine mockSourceDataLine;

    @DisplayName("default audio line")
    @Nested
    public class DefaultAudioLine {
        @DisplayName("should create default audio playback")
        @Test
        public void shouldCreateDefaultAudioPlayback() throws Exception {
            when(mockAudioLineProvider.isLineSupported(any())).thenReturn(true);
            when(mockAudioLineProvider.getSourceLine(any())).thenReturn(mockSourceDataLine);

            AudioPlaybackFactory audioPlaybackFactory = new AudioPlaybackFactory(mockAudioLineProvider);
            AudioPlayback audioPlayback = audioPlaybackFactory.create(AudioType.DEFAULT_TYPE);

            Assertions.assertNotNull(audioPlayback);
            Assertions.assertInstanceOf(DefaultAudioPlayback.class, audioPlayback);
        }
    }

}
