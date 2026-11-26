package io.github.Makday.job;

import io.github.Makday.observer.Subject;
import io.github.Makday.settings.ConversionSettings;

/** State context + holder of the job data. Announces every transition to observers. */
public class ConversionJob {
  private final String input;
  private final String output;
  private final ConversionSettings settings;
  private final Subject<JobEvent> events;
  private JobState state = new QueuedState();

  public ConversionJob(String input, String output, ConversionSettings settings,
      Subject<JobEvent> events) {
    this.input = input;
    this.output = output;
    this.settings = settings;
    this.events = events;
  }

  public String input() { return input; }
  public String output() { return output; }
  public ConversionSettings settings() { return settings; }
  public String stateName() { return state.name(); }

  public void start()   { move(state.start()); }
  public void succeed() { move(state.succeed()); }
  public void fail()    { move(state.fail()); }
  public void cancel()  { move(state.cancel()); }
  public void retry()   { move(state.retry()); }

  private void move(JobState next) {
    state = next;
    events.publish(new JobEvent(output, next.name()));
  }
}
