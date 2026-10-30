package io.github.Makday.converter;

import java.io.IOException;

import io.github.Makday.settings.ConversionSettings;

public interface Converter {
  void convert(String input, String output, ConversionSettings settings)
      throws IOException, InterruptedException;
}
