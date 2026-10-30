package io.github.Makday.converter.flac;

import io.github.Makday.converter.Converter;
import io.github.Makday.converter.ConverterCreator;

public class FlacConverterCreator extends ConverterCreator {
  @Override
  protected Converter createConverter() {
    return new FlacConverter();
  }
}
