package io.github.Makday.converter.mp3;

import io.github.Makday.converter.Converter;
import io.github.Makday.converter.ConverterCreator;

public class Mp3ConverterCreator extends ConverterCreator {
  @Override
  protected Converter createConverter() {
    return new Mp3Converter();
  }
}
