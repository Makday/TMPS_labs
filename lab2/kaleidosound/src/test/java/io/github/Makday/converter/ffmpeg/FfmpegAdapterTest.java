package io.github.Makday.converter.ffmpeg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.github.Makday.config.AppConfig;
import io.github.Makday.settings.ConversionSettings;

class FfmpegAdapterTest {
  private static class Mp3Like extends FfmpegConverter {
    @Override
    protected List<String> codecArgs() {
      return List.of("-codec:a", "libmp3lame");
    }
  }

  @Test
  void translatesCallIntoFfmpegCommand() {
    List<String> cmd = new Mp3Like().buildCommand("in.flac", "out.mp3",
        new ConversionSettings.Builder().build());

    assertEquals(AppConfig.INSTANCE.ffmpegPath(), cmd.get(0));
    assertTrue(cmd.containsAll(List.of("-i", "in.flac", "-codec:a", "libmp3lame")));
    assertEquals("out.mp3", cmd.get(cmd.size() - 1));
  }

  @Test
  void translatesSettingsIntoFfmpegFlags() {
    ConversionSettings settings =
        new ConversionSettings.Builder().bitrate(192).sampleRate(44100).channels(2).build();

    List<String> cmd = new FfmpegConverter().buildCommand("in.wav", "out.ogg", settings);

    assertTrue(cmd.containsAll(List.of("-b:a", "192k", "-ar", "44100", "-ac", "2")));
    assertEquals("out.ogg", cmd.get(cmd.size() - 1));
  }
}
