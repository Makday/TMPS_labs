package io.github.Makday.converter;

import java.io.IOException;

import io.github.Makday.settings.ConversionSettings;

public abstract class ConverterCreator {
  protected abstract Converter createConverter();

  public void convert(String input, String output, ConversionSettings settings)
      throws IOException, InterruptedException {
    createConverter().convert(input, output, settings);
  }
}
