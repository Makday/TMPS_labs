package io.github.Makday.job;

/** Each transition returns the next state; forbidden ones throw. */
public interface JobState {
  default JobState start()   { throw illegal("start"); }
  default JobState succeed() { throw illegal("succeed"); }
  default JobState fail()    { throw illegal("fail"); }
  default JobState cancel()  { throw illegal("cancel"); }
  default JobState retry()   { throw illegal("retry"); }

  default String name() {
    return getClass().getSimpleName().replace("State", "");
  }

  private IllegalStateException illegal(String action) {
    return new IllegalStateException("Cannot " + action + " a job in state " + name());
  }
}
