package io.github.Makday.converter.decorator;

import java.io.IOException;

import io.github.Makday.converter.Converter;
import io.github.Makday.settings.ConversionSettings;

/** Concrete decorator: reports elapsed time of the wrapped conversion. */
public class TimingConverter extends ConverterDecorator {
  public TimingConverter(Converter wrapped) {
    super(wrapped);
  }

  @Override
  public void convert(String input, String output, ConversionSettings settings)
      throws IOException, InterruptedException {
    long start = System.nanoTime();
    try {
      super.convert(input, output, settings);
    } finally {
      System.out.printf("Took %d ms%n", (System.nanoTime() - start) / 1_000_000);
    }
  }
}
