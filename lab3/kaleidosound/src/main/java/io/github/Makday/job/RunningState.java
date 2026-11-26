package io.github.Makday.job;

class RunningState implements JobState {
  @Override public JobState succeed() { return new SucceededState(); }
  @Override public JobState fail()    { return new FailedState(); }
}
