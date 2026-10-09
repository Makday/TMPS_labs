package com.example.FlowForge.Actions;

import com.example.FlowForge.Context;

public class LogAction implements Action{
    private final String message;

    public LogAction(String message) {
        this.message = message;
    }

    @Override
    public void execute(Context ctx) {
        System.out.println("[LOG]" +  message);
    }
}
