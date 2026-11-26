package io.github.Makday.converter.decorator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.github.Makday.converter.Converter;
import io.github.Makday.settings.ConversionSettings;

class DecoratorTest {
  private static final ConversionSettings SETTINGS = new ConversionSettings.Builder().build();

  /** Test decorator that records when it is entered and left. */
  private static class Tag extends ConverterDecorator {
    private final String name;
    private final List<String> log;

    Tag(String name, List<String> log, Converter wrapped) {
      super(wrapped);
      this.name = name;
      this.log = log;
    }

    @Override
    public void convert(String in, String out, ConversionSettings s)
        throws IOException, InterruptedException {
      log.add(name + ">");
      super.convert(in, out, s);
      log.add("<" + name);
    }
  }

  @Test
  void decoratorsStackInAnyOrder() throws Exception {
    List<String> log = new ArrayList<>();
    Converter core = (in, out, s) -> log.add("core");

    new Tag("A", log, new Tag("B", log, core)).convert("i", "o", SETTINGS);
    assertEquals(List.of("A>", "B>", "core", "<B", "<A"), log);

    log.clear();
    new Tag("B", log, new Tag("A", log, core)).convert("i", "o", SETTINGS);
    assertEquals(List.of("B>", "A>", "core", "<A", "<B"), log);
  }

  @Test
  void realDecoratorsPassArgumentsThroughUnchanged() throws Exception {
    List<String> seen = new ArrayList<>();
    Converter core = (in, out, s) -> seen.add(in + "|" + out + "|" + (s == SETTINGS));

    Converter stacked = new LoggingConverter(new TimingConverter(core));
    stacked.convert("a.flac", "b.mp3", SETTINGS);

    assertEquals(List.of("a.flac|b.mp3|true"), seen);
  }

  @Test
  void timingDecoratorDoesNotSwallowErrors() {
    Converter failing = (in, out, s) -> {
      throw new IOException("boom");
    };
    assertThrows(IOException.class,
        () -> new TimingConverter(failing).convert("i", "o", SETTINGS));
  }
}
