package io.github.Makday;

import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

import io.github.Makday.converter.Converter;
import io.github.Makday.converter.ConverterCreator;
import io.github.Makday.converter.decorator.LoggingConverter;
import io.github.Makday.converter.decorator.TimingConverter;
import io.github.Makday.converter.ffmpeg.DefaultConverterCreator;
import io.github.Makday.converter.flac.FlacConverterCreator;
import io.github.Makday.converter.mp3.Mp3ConverterCreator;
import io.github.Makday.converter.wav.WavConverterCreator;
import io.github.Makday.settings.ConversionSettings;

class ConverterFacade {
  private final Map<String, ConverterCreator> creators;
  private final ConverterCreator defaultCreator;
  private final List<UnaryOperator<Converter>> decorators;

  ConverterFacade() {
    this(Map.of(
        "mp3", new Mp3ConverterCreator(),
        "wav", new WavConverterCreator(),
        "flac", new FlacConverterCreator()),
        new DefaultConverterCreator(),
        List.of(TimingConverter::new, LoggingConverter::new));
  }

  ConverterFacade(Map<String, ConverterCreator> creators, ConverterCreator defaultCreator,
      List<UnaryOperator<Converter>> decorators) {
    this.creators = creators;
    this.defaultCreator = defaultCreator;
    this.decorators = decorators;
  }

  public void convert(String input, String output, ConversionSettings settings) {
    try {
      String ext = output.substring(output.lastIndexOf('.') + 1).toLowerCase();
      Converter converter = creators.getOrDefault(ext, defaultCreator).create();
      for (UnaryOperator<Converter> decorator : decorators) {
        converter = decorator.apply(converter);
      }
      converter.convert(input, output, settings);
      System.out.println("Done: " + output);
    } catch (Exception e) {
      System.err.println("Conversion failed: " + e.getMessage());
    }
  }
}
