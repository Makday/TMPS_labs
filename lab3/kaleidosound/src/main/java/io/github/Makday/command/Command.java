package io.github.Makday.command;

public interface Command {
  void execute();

  void undo();
}
