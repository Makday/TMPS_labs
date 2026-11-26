package io.github.Makday.observer;

public interface Observer<E> {
  void onEvent(E event);
}
