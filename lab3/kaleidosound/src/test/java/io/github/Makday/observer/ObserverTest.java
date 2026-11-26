package io.github.Makday.observer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ObserverTest {
  @Test
  void allSubscribedObserversAreNotified() {
    Subject<String> subject = new Subject<>();
    List<String> a = new ArrayList<>();
    List<String> b = new ArrayList<>();
    subject.subscribe(a::add);
    subject.subscribe(b::add);

    subject.publish("x");

    assertEquals(List.of("x"), a);
    assertEquals(List.of("x"), b);
  }

  @Test
  void unsubscribedObserverIsNotNotified() {
    Subject<String> subject = new Subject<>();
    List<String> log = new ArrayList<>();
    Observer<String> o = log::add;
    subject.subscribe(o);
    subject.unsubscribe(o);

    subject.publish("x");

    assertEquals(List.of(), log);
  }
}
