package com.github.fedverdev.audio.playback;

import com.github.fedverdev.audio.enums.AudioType;
import com.github.fedverdev.audio.provider.AudioLineProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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

        @DisplayName("should correctly playback")
        @Test
        public void shouldPlaybackCorrectData() throws LineUnavailableException {
            when(mockAudioLineProvider.isLineSupported(any())).thenReturn(true);
            when(mockAudioLineProvider.getSourceLine(any())).thenReturn(mockSourceDataLine);

            byte[] expected = new byte[] {1, 2, 3, 4, 5};

            when(mockSourceDataLine.write(any((byte[].class)), anyInt(), anyInt()))
                    .thenAnswer(invocation -> {
                        byte[] actual = invocation.getArgument(0);
                        int length = invocation.getArgument(2);
                        Assertions.assertArrayEquals(expected, actual);
                        return length;
                    });

            AudioPlaybackFactory audioPlaybackFactory = new AudioPlaybackFactory(mockAudioLineProvider);
            AudioPlayback audioPlayback = audioPlaybackFactory.create(AudioType.DEFAULT_TYPE);

            Assertions.assertNotNull(audioPlayback);
            Assertions.assertInstanceOf(DefaultAudioPlayback.class, audioPlayback);

            audioPlayback.playbackFrom(expected);

        }
    }

}
