package io.github.Makday.converter;

public class Mp3ConverterCreator extends ConverterCreator {
  @Override
  protected Converter createConverter() {
    return new Mp3Converter();
  }
}
