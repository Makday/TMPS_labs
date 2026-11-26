package io.github.Makday.config;

public enum AppConfig {
  INSTANCE;

  private final String ffmpegPath = System.getenv().getOrDefault("FFMPEG_PATH", "ffmpeg");

  public String ffmpegPath() {
    return ffmpegPath;
  }
}
