package io.github.Makday.converter.decorator;

import java.io.IOException;

import io.github.Makday.converter.Converter;
import io.github.Makday.settings.ConversionSettings;

/** Concrete decorator: logs the request before delegating. */
public class LoggingConverter extends ConverterDecorator {
  public LoggingConverter(Converter wrapped) {
    super(wrapped);
  }

  @Override
  public void convert(String input, String output, ConversionSettings settings)
      throws IOException, InterruptedException {
    System.out.println("Converting " + input + " -> " + output);
    super.convert(input, output, settings);
  }
}
