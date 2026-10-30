package io.github.Makday;

import io.github.Makday.converter.Converter;
import io.github.Makday.converter.ConverterFactory;
import io.github.Makday.settings.ConversionSettings;

class ConverterFacade {
  public void convert(String input, String output, ConversionSettings settings) {
    try {
      Converter converter = ConverterFactory.create(output);
      converter.convert(input, output, settings);
      System.out.println("Done: " + output);
    } catch (Exception e) {
      System.err.println("Conversion failed: " + e.getMessage());
    }
  }
}
