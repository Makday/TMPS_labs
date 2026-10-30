package io.github.Makday.converter;

import java.util.List;

class Mp3Converter extends FfmpegConverter {
  @Override
  protected List<String> codecArgs() {
    return List.of("-codec:a", "libmp3lame");
  }
}
