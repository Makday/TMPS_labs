package io.github.Makday.observer;

import io.github.Makday.job.JobEvent;

public class StatsObserver implements Observer<JobEvent> {
  private int succeeded;
  private int failed;

  @Override
  public void onEvent(JobEvent e) {
    if (e.state().equals("Succeeded")) succeeded++;
    if (e.state().equals("Failed")) failed++;
  }

  @Override
  public String toString() {
    return "succeeded=" + succeeded + ", failed=" + failed;
  }
}
