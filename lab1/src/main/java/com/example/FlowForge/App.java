package com.example.FlowForge;

import com.example.FlowForge.actions.LogAction;
import com.example.FlowForge.core.Context;
import com.example.FlowForge.core.Workflow;
import com.example.FlowForge.events.EventBus;

import java.util.List;

public class App {
    public static void main(String[] args) {
        EventBus bus = new EventBus();
        Workflow wf = new Workflow("hello-flow",
                List.of(new LogAction("Step 1"), new LogAction("Step 2")));

        bus.subscribe("USER_SIGNUP", (type, ctx) -> wf.run(ctx));
        bus.publish("USER_SIGNUP", new Context());
    }
}
