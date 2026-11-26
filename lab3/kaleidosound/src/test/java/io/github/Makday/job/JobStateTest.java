package io.github.Makday.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import io.github.Makday.observer.Subject;
import io.github.Makday.settings.ConversionSettings;

class JobStateTest {
  private static ConversionJob job() {
    return new ConversionJob("in", "out", new ConversionSettings.Builder().build(), new Subject<>());
  }

  @Test
  void allowsValidTransitions() {
    ConversionJob j = job();
    j.start();
    j.fail();
    j.retry();
    j.start();
    j.succeed();
    assertEquals("Succeeded", j.stateName());
  }

  @Test
  void rejectsForbiddenTransitions() {
    ConversionJob j = job();
    assertThrows(IllegalStateException.class, j::succeed); // Queued cannot succeed
    j.start();
    j.succeed();
    assertThrows(IllegalStateException.class, j::start);   // Succeeded cannot restart
  }
}
