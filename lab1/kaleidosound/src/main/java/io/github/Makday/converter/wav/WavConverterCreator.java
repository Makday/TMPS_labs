package io.github.Makday.converter.wav;

import io.github.Makday.converter.Converter;
import io.github.Makday.converter.ConverterCreator;

public class WavConverterCreator extends ConverterCreator {
  @Override
  protected Converter createConverter() {
    return new WavConverter();
  }
}
