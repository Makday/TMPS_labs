package io.github.Makday.converter;

public class FlacConverterCreator extends ConverterCreator {
  @Override
  protected Converter createConverter() {
    return new FlacConverter();
  }
}
