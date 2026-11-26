package io.github.Makday.command;

import java.util.ArrayDeque;
import java.util.Deque;

/** Invoker: runs commands and remembers them for undo. */
public class CommandHistory {
  private final Deque<Command> done = new ArrayDeque<>();

  public void run(Command command) {
    command.execute();
    done.push(command);
  }

  public boolean undo() {
    if (done.isEmpty()) return false;
    done.pop().undo();
    return true;
  }
}
