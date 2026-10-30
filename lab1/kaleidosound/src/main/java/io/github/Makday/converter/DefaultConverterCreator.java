package io.github.Makday.converter;

public class DefaultConverterCreator extends ConverterCreator {
  @Override
  protected Converter createConverter() {
    return new FfmpegConverter();
  }
}
