package com.example.flowforge.events;

import com.example.flowforge.core.Context;

public interface EventListener {
    void onEvent(String eventType, Context ctx);
}
