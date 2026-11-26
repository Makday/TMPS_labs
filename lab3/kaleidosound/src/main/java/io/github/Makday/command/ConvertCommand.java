package io.github.Makday.command;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import io.github.Makday.ConverterFacade;
import io.github.Makday.job.ConversionJob;

/** Receiver of the real work is ConverterFacade; this command adds job tracking and undo. */
public class ConvertCommand implements Command {
  private final ConversionJob job;
  private final ConverterFacade facade;
  private Path backup;
  private boolean succeeded;

  public ConvertCommand(ConversionJob job, ConverterFacade facade) {
    this.job = job;
    this.facade = facade;
  }

  @Override
  public void execute() {
    job.start();
    try {
      Path out = Path.of(job.output());
      if (Files.exists(out)) { // keep the file ffmpeg -y would overwrite
        backup = Files.createTempFile("kaleidosound", ".bak");
        Files.move(out, backup, StandardCopyOption.REPLACE_EXISTING);
      }
      facade.convert(job.input(), job.output(), job.settings());
      succeeded = true;
      job.succeed();
    } catch (Exception e) {
      System.err.println("Conversion failed: " + e.getMessage());
      restoreBackup();
      job.fail();
    }
  }

  @Override
  public void undo() {
    if (!succeeded) return; // nothing was produced
    job.cancel();
    try {
      Files.deleteIfExists(Path.of(job.output()));
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
    restoreBackup();
    succeeded = false;
  }

  private void restoreBackup() {
    if (backup == null) return;
    try {
      Files.move(backup, Path.of(job.output()), StandardCopyOption.REPLACE_EXISTING);
      backup = null;
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
