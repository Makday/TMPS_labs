package com.example.flowforge.actions;

import com.example.flowforge.core.Context;

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
