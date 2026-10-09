package com.example.FlowForge.core;

import com.example.FlowForge.actions.Action;

import java.util.List;

public class Workflow {
    private final String name;
    private final List<Action> actions;

    public Workflow(String name, List<Action> actions) {
        this.name = name;
        this.actions = actions;
    }

    public void run(Context ctx) {
        System.out.println("Running " + name);
        actions.forEach(a -> a.execute(ctx));
    }
}
