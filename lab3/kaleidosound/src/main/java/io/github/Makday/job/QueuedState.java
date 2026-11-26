package io.github.Makday.job;

class QueuedState implements JobState {
  @Override public JobState start()  { return new RunningState(); }
  @Override public JobState cancel() { return new CancelledState(); }
}
