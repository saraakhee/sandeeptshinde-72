package com.example.demo.configuration;

import com.example.demo.model.MiddlewarePlugin;
import com.example.demo.service.PluginRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PluginConfig {

    private final List<MiddlewarePlugin> plugins;
    private final PluginRegistry registry;

    public PluginConfig(List<MiddlewarePlugin> plugins, PluginRegistry registry) {
        this.plugins = plugins;
        this.registry = registry;
    }

    @PostConstruct
    public void init() {
        for (MiddlewarePlugin plugin : plugins) {
            registry.register(plugin);
        }
    }
}
