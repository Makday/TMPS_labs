package io.github.Makday.converter;

public class WavConverterCreator extends ConverterCreator {
  @Override
  protected Converter createConverter() {
    return new WavConverter();
  }
}
