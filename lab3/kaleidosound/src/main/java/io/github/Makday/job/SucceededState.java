package io.github.Makday.job;

class SucceededState implements JobState {
  @Override public JobState cancel() { return new CancelledState(); } // used by undo
}
