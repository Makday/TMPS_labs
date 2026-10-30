package io.github.Makday.converter.wav;

import java.util.List;

import io.github.Makday.converter.ffmpeg.FfmpegConverter;

class WavConverter extends FfmpegConverter {
  @Override
  protected List<String> codecArgs() {
    return List.of("-codec:a", "pcm_s16le");
  }
}
