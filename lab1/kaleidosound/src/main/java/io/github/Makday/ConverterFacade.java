package io.github.Makday;

import java.util.Map;

import io.github.Makday.converter.ConverterCreator;
import io.github.Makday.converter.DefaultConverterCreator;
import io.github.Makday.converter.FlacConverterCreator;
import io.github.Makday.converter.Mp3ConverterCreator;
import io.github.Makday.converter.WavConverterCreator;
import io.github.Makday.settings.ConversionSettings;

class ConverterFacade {
  private static final Map<String, ConverterCreator> CREATORS = Map.of(
      "mp3", new Mp3ConverterCreator(),
      "wav", new WavConverterCreator(),
      "flac", new FlacConverterCreator());

  public void convert(String input, String output, ConversionSettings settings) {
    try {
      String ext = output.substring(output.lastIndexOf('.') + 1).toLowerCase();
      ConverterCreator creator = CREATORS.getOrDefault(ext, new DefaultConverterCreator());
      creator.convert(input, output, settings);
      System.out.println("Done: " + output);
    } catch (Exception e) {
      System.err.println("Conversion failed: " + e.getMessage());
    }
  }
}
