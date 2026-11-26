package io.github.Makday.converter;

import java.io.IOException;

import io.github.Makday.settings.ConversionSettings;

public abstract class ConverterCreator {
  protected abstract Converter createConverter();

  /** Returns a fresh converter so callers can wrap it (e.g. with decorators). */
  public Converter create() {
    return createConverter();
  }

  public void convert(String input, String output, ConversionSettings settings)
      throws IOException, InterruptedException {
    create().convert(input, output, settings);
  }
}
