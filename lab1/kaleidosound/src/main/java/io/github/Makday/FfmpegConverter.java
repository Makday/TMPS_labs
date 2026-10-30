package io.github.Makday;

import java.io.IOException;

class FfmpegConverter implements Converter {
  @Override
  public void convert(String input, String output) throws IOException, InterruptedException {
    ProcessBuilder pb = new ProcessBuilder(
        "ffmpeg", "-y", "-loglevel", "error", "-i", input, "-vn", output);
    pb.inheritIO();
    int exitCode = pb.start().waitFor();
    if (exitCode != 0) {
      throw new IOException("FFmpeg failed with exit code " + exitCode);
    }
  }
}
