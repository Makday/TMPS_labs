package io.github.Makday.converter.flac;

import java.util.List;

import io.github.Makday.converter.ffmpeg.FfmpegConverter;

class FlacConverter extends FfmpegConverter {
  @Override
  protected List<String> codecArgs() {
    return List.of("-codec:a", "flac");
  }
}
