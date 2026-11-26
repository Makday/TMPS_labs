package io.github.Makday.observer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Subject<E> {
  private final List<Observer<E>> observers = new CopyOnWriteArrayList<>();

  public void subscribe(Observer<E> o) {
    observers.add(o);
  }

  public void unsubscribe(Observer<E> o) {
    observers.remove(o);
  }

  public void publish(E event) {
    observers.forEach(o -> o.onEvent(event));
  }
}
