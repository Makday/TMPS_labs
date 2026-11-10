package io.github.Makday.converter.mp3;

import java.util.List;

import io.github.Makday.converter.ffmpeg.FfmpegConverter;

class Mp3Converter extends FfmpegConverter {
  @Override
  protected List<String> codecArgs() {
    return List.of("-codec:a", "libmp3lame");
  }
}
