package com.example.FlowForge;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private final Map<String, Object> data = new HashMap<>();
    public void put(String k, Object v) { data.put(k, v); }
    public Object get(String k) { return data.get(k); }
}