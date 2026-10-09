package com.example.FlowForge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {
    private final Map<String, List<EventListener>> listeners = new HashMap<>();

    public void subscribe(String type, EventListener l) {
        listeners.computeIfAbsent(type, k -> new ArrayList<>()).add(l);
    }

    public void publish(String type, Context ctx) {
        listeners.getOrDefault(type, List.of()).forEach(l -> l.onEvent(type, ctx));
    }
}
