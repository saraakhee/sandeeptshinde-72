package com.example.demo.service;

import com.example.demo.model.MiddlewarePlugin;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PluginRegistry {
    private final Map<String, MiddlewarePlugin> pluginMap = new HashMap<>();

    public void register(MiddlewarePlugin plugin) {
        pluginMap.put(plugin.getName(), plugin);
    }

    public MiddlewarePlugin getPlugin(String name) {
        return pluginMap.get(name);
    }
}
