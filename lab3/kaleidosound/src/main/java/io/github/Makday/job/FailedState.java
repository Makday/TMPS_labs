package io.github.Makday.job;

class FailedState implements JobState {
  @Override public JobState retry() { return new QueuedState(); }
}
