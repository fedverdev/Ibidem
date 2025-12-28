package com.github.fedverdev.audio.capture;

import com.github.fedverdev.audio.enums.AudioType;
import com.github.fedverdev.audio.provider.AudioLineProvider;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sound.sampled.TargetDataLine;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@DisplayName("Capture")
@ExtendWith(MockitoExtension.class)
@Tag("Audio")
public class AudioCaptureTest {
    @Mock
    AudioLineProvider mockAudioLineProvider;

    @Mock
    TargetDataLine mockTargetDataLine;

    @DisplayName("default audio line")
    @Nested
    public class DefaultAudioLine {

        @DisplayName("should create default audio capture")
        @Test
        public void shouldCreateDefaultAudioCapture() throws Exception {
            when(mockAudioLineProvider.isLineSupported(any())).thenReturn(true);
            when(mockAudioLineProvider.getTargetLine(any())).thenReturn(mockTargetDataLine);

            AudioCaptureFactory audioCaptureFactory = new AudioCaptureFactory(mockAudioLineProvider);
            AudioCapture audioCapture = audioCaptureFactory.create(AudioType.DEFAULT_TYPE);

            Assertions.assertNotNull(audioCapture);
            Assertions.assertInstanceOf(DefaultAudioCapture.class, audioCapture);
        }

        @DisplayName("should correctly capture")
        @Test
        public void shouldCaptureCorrectData() throws Exception {
            byte[] expected = new byte[] {1, 2, 3, 4, 5};
            byte[] actual = new byte[5];

            when(mockTargetDataLine.read(any(byte[].class), anyInt(), anyInt()))
                    .thenAnswer(invocation -> {
                        byte[] buffer = invocation.getArgument(0);
                        int offset = invocation.getArgument(1);
                        int length = invocation.getArgument(2);

                        System.arraycopy(expected, 0, buffer, offset, length);
                        return length;
                    });

            when(mockAudioLineProvider.isLineSupported(any())).thenReturn(true);
            when(mockAudioLineProvider.getTargetLine(any())).thenReturn(mockTargetDataLine);

            AudioCaptureFactory audioCaptureFactory = new AudioCaptureFactory(mockAudioLineProvider);
            AudioCapture audioCapture = audioCaptureFactory.create(AudioType.DEFAULT_TYPE);

            audioCapture.captureTo(actual);

            Assertions.assertNotNull(audioCapture);
            Assertions.assertInstanceOf(DefaultAudioCapture.class, audioCapture);

            Assertions.assertArrayEquals(expected, actual);
        }
    }
}
