package io.github.Makday.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import io.github.Makday.ConverterFacade;
import io.github.Makday.converter.Converter;
import io.github.Makday.converter.ConverterCreator;
import io.github.Makday.job.ConversionJob;
import io.github.Makday.job.JobEvent;
import io.github.Makday.observer.Subject;
import io.github.Makday.settings.ConversionSettings;

class ConvertCommandTest {
  @TempDir Path dir;
  private final Subject<JobEvent> events = new Subject<>();
  private final List<String> seen = new ArrayList<>();

  private static ConverterFacade facade(Converter converter) {
    ConverterCreator creator = new ConverterCreator() {
      @Override
      protected Converter createConverter() {
        return converter;
      }
    };
    return new ConverterFacade(Map.of(), creator, List.of());
  }

  private ConvertCommand command(Path out, Converter converter) {
    events.subscribe(e -> seen.add(e.state()));
    ConversionJob job = new ConversionJob("in", out.toString(),
        new ConversionSettings.Builder().build(), events);
    return new ConvertCommand(job, facade(converter));
  }

  private static final Converter WRITES_NEW = (in, out, s) -> Files.writeString(Path.of(out), "new");

  @Test
  void undoRestoresOverwrittenFile() throws IOException {
    Path out = dir.resolve("out.mp3");
    Files.writeString(out, "original");
    ConvertCommand cmd = command(out, WRITES_NEW);

    cmd.execute();
    assertEquals("new", Files.readString(out));
    cmd.undo();

    assertEquals("original", Files.readString(out));
  }

  @Test
  void undoRemovesOutputWhenNothingWasOverwritten() {
    Path out = dir.resolve("out.mp3");
    ConvertCommand cmd = command(out, WRITES_NEW);

    cmd.execute();
    cmd.undo();

    assertFalse(Files.exists(out));
  }

  @Test
  void commandDrivesStateAndNotifiesObservers() {
    ConvertCommand cmd = command(dir.resolve("out.mp3"), WRITES_NEW);

    cmd.execute();
    cmd.undo();

    assertEquals(List.of("Running", "Succeeded", "Cancelled"), seen);
  }

  @Test
  void failedConversionMarksJobFailedAndKeepsOriginal() throws IOException {
    Path out = dir.resolve("out.mp3");
    Files.writeString(out, "original");
    ConvertCommand cmd = command(out, (in, o, s) -> { throw new IOException("boom"); });

    cmd.execute();

    assertEquals(List.of("Running", "Failed"), seen);
    assertEquals("original", Files.readString(out));
  }
}
