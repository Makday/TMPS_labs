package io.github.Makday.converter.decorator;

import java.io.IOException;

import io.github.Makday.converter.Converter;
import io.github.Makday.settings.ConversionSettings;

/** Decorator: adds elapsed-time reporting to any Converter. */
public class TimingConverter implements Converter {
  private final Converter delegate;

  public TimingConverter(Converter delegate) {
    this.delegate = delegate;
  }

  @Override
  public void convert(String input, String output, ConversionSettings settings)
      throws IOException, InterruptedException {
    long start = System.nanoTime();
    try {
      delegate.convert(input, output, settings);
    } finally {
      System.out.printf("Took %d ms%n", (System.nanoTime() - start) / 1_000_000);
    }
  }
}
