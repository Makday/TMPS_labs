package io.github.Makday.converter.ffmpeg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.github.Makday.config.AppConfig;
import io.github.Makday.converter.Converter;
import io.github.Makday.settings.ConversionSettings;

public class FfmpegConverter implements Converter {
  protected List<String> codecArgs() {
    return List.of();
  }

  /** Translates our domain call into the ffmpeg command line (the adaptee's interface). */
  public List<String> buildCommand(String input, String output, ConversionSettings settings) {
    List<String> cmd = new ArrayList<>(List.of(
        AppConfig.INSTANCE.ffmpegPath(), "-y", "-loglevel", "error", "-i", input, "-vn"));
    cmd.addAll(codecArgs());
    cmd.addAll(settings.toFfmpegArgs());
    cmd.add(output);
    return cmd;
  }

  @Override
  public void convert(String input, String output, ConversionSettings settings)
      throws IOException, InterruptedException {
    ProcessBuilder pb = new ProcessBuilder(buildCommand(input, output, settings));
    pb.inheritIO();
    int exitCode = pb.start().waitFor();
    if (exitCode != 0) {
      throw new IOException("FFmpeg failed with exit code " + exitCode);
    }
  }
}
