package com.example.FlowForge;

import com.example.FlowForge.Actions.LogAction;

public class App {
    public static void main(String[] args) {
        EventBus bus = new EventBus();
        Workflow wf = new Workflow("hello-flow",
                java.util.List.of(new LogAction("Step 1"), new LogAction("Step 2")));

        bus.subscribe("USER_SIGNUP", (type, ctx) -> wf.run(ctx));
        bus.publish("USER_SIGNUP", new Context());
    }
}
