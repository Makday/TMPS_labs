package io.github.Makday.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.github.Makday.config.AppConfig;
import io.github.Makday.settings.ConversionSettings;

public class FfmpegConverter implements Converter {
  protected List<String> codecArgs() {
    return List.of();
  }

  @Override
  public void convert(String input, String output, ConversionSettings settings)
      throws IOException, InterruptedException {
    List<String> cmd = new ArrayList<>(List.of(
        AppConfig.INSTANCE.ffmpegPath(), "-y", "-loglevel", "error", "-i", input, "-vn"));
    cmd.addAll(codecArgs());
    cmd.addAll(settings.toFfmpegArgs());
    cmd.add(output);

    ProcessBuilder pb = new ProcessBuilder(cmd);
    pb.inheritIO();
    int exitCode = pb.start().waitFor();
    if (exitCode != 0) {
      throw new IOException("FFmpeg failed with exit code " + exitCode);
    }
  }
}
