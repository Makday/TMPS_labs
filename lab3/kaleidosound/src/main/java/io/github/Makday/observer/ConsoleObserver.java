package io.github.Makday.observer;

import io.github.Makday.job.JobEvent;

public class ConsoleObserver implements Observer<JobEvent> {
  @Override
  public void onEvent(JobEvent e) {
    System.out.println("[" + e.state() + "] " + e.output());
  }
}
