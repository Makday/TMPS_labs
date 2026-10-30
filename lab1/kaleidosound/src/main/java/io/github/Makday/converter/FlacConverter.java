package io.github.Makday.converter;

import java.util.List;

class FlacConverter extends FfmpegConverter {
  @Override
  protected List<String> codecArgs() {
    return List.of("-codec:a", "flac");
  }
}
