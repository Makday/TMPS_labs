package io.github.Makday;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

import org.junit.jupiter.api.Test;

import io.github.Makday.converter.Converter;
import io.github.Makday.converter.ConverterCreator;
import io.github.Makday.converter.decorator.ConverterDecorator;
import io.github.Makday.settings.ConversionSettings;

class ConverterFacadeTest {
  private static final ConversionSettings SETTINGS = new ConversionSettings.Builder().build();

  private static ConverterCreator stub(String name, List<String> log) {
    return new ConverterCreator() {
      @Override
      protected Converter createConverter() {
        return (in, out, s) -> log.add(name + ":" + in + "->" + out);
      }
    };
  }

  @Test
  void routesByOutputExtensionAndFallsBackToDefault() {
    List<String> log = new ArrayList<>();
    ConverterFacade facade = new ConverterFacade(
        Map.of("mp3", stub("mp3", log)), stub("default", log), List.of());

    facade.convert("a.wav", "b.MP3", SETTINGS);
    facade.convert("a.wav", "b.ogg", SETTINGS);

    assertEquals(List.of("mp3:a.wav->b.MP3", "default:a.wav->b.ogg"), log);
  }

  @Test
  void appliesDecoratorsAroundTheCreatedConverter() {
    List<String> log = new ArrayList<>();
    UnaryOperator<Converter> marker = c -> new ConverterDecorator(c) {
      @Override
      public void convert(String in, String out, ConversionSettings s)
          throws java.io.IOException, InterruptedException {
        log.add("before");
        super.convert(in, out, s);
        log.add("after");
      }
    };
    ConverterFacade facade = new ConverterFacade(
        Map.of(), stub("core", log), List.of(marker));

    facade.convert("a.wav", "b.ogg", SETTINGS);

    assertEquals(List.of("before", "core:a.wav->b.ogg", "after"), log);
  }
}
