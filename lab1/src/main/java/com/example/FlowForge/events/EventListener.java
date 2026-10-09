package com.example.FlowForge.events;

import com.example.FlowForge.core.Context;

public interface EventListener {
    void onEvent(String eventType, Context ctx);
}
