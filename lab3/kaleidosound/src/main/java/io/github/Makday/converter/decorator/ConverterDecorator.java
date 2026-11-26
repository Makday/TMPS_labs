package io.github.Makday.converter.decorator;

import java.io.IOException;

import io.github.Makday.converter.Converter;
import io.github.Makday.settings.ConversionSettings;

/** Base decorator: implements Converter and forwards every call to the wrapped one. */
public abstract class ConverterDecorator implements Converter {
  protected final Converter wrapped;

  protected ConverterDecorator(Converter wrapped) {
    this.wrapped = wrapped;
  }

  @Override
  public void convert(String input, String output, ConversionSettings settings)
      throws IOException, InterruptedException {
    wrapped.convert(input, output, settings);
  }
}
