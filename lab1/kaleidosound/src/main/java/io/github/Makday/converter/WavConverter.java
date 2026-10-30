package io.github.Makday.converter;

import java.util.List;

class WavConverter extends FfmpegConverter {
  @Override
  protected List<String> codecArgs() {
    return List.of("-codec:a", "pcm_s16le");
  }
}
