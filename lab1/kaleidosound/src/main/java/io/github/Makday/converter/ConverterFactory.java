package io.github.Makday.converter;

public final class ConverterFactory {
  private ConverterFactory() {
  }

  public static Converter create(String outputPath) {
    String ext = outputPath.substring(outputPath.lastIndexOf('.') + 1).toLowerCase();
    return switch (ext) {
      case "mp3" -> new Mp3Converter();
      case "wav" -> new WavConverter();
      case "flac" -> new FlacConverter();
      default -> new FfmpegConverter();
    };
  }
}
