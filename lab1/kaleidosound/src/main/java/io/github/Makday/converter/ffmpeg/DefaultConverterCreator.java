package io.github.Makday.converter.ffmpeg;

import io.github.Makday.converter.Converter;
import io.github.Makday.converter.ConverterCreator;

public class DefaultConverterCreator extends ConverterCreator {
  @Override
  protected Converter createConverter() {
    return new FfmpegConverter();
  }
}
