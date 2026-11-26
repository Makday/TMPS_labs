package io.github.Makday.cli;

import java.util.Map;
import java.util.Scanner;

import io.github.Makday.ConverterFacade;
import io.github.Makday.command.CommandHistory;
import io.github.Makday.command.ConvertCommand;
import io.github.Makday.job.ConversionJob;
import io.github.Makday.job.JobEvent;
import io.github.Makday.observer.ConsoleObserver;
import io.github.Makday.observer.Observer;
import io.github.Makday.observer.StatsObserver;
import io.github.Makday.observer.Subject;
import io.github.Makday.settings.ConversionSettings;

public class Shell {
  private final Subject<JobEvent> events = new Subject<>();
  private final CommandHistory history = new CommandHistory();
  private final ConverterFacade facade = new ConverterFacade();
  private final StatsObserver stats = new StatsObserver();
  private final Map<String, Observer<JobEvent>> observers =
      Map.of("console", new ConsoleObserver(), "stats", stats);

  public void run(Scanner in) {
    events.subscribe(observers.get("console"));
    System.out.println("Commands: convert <in> <out> [kbps] | undo | stats"
        + " | subscribe|unsubscribe <console|stats> | quit");
    while (in.hasNextLine()) {
      String[] t = in.nextLine().trim().split("\\s+");
      try {
        switch (t[0]) {
          case "convert" -> {
            ConversionSettings.Builder b = new ConversionSettings.Builder();
            if (t.length > 3) b.bitrate(Integer.parseInt(t[3]));
            history.run(new ConvertCommand(
                new ConversionJob(t[1], t[2], b.build(), events), facade));
          }
          case "undo" -> System.out.println(history.undo() ? "Undone" : "Nothing to undo");
          case "stats" -> System.out.println(stats);
          case "subscribe" -> events.subscribe(observers.get(t[1]));
          case "unsubscribe" -> events.unsubscribe(observers.get(t[1]));
          case "quit" -> { return; }
          default -> System.out.println("Unknown command");
        }
      } catch (RuntimeException e) {
        System.out.println("Error: " + e.getMessage());
      }
    }
  }
}
