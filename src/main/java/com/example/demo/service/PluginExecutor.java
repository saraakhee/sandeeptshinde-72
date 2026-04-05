package com.example.demo.service;

import com.example.demo.model.MessageContext;
import com.example.demo.model.MiddlewarePlugin;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PluginExecutor {

    private final PluginRegistry registry;

    public PluginExecutor(PluginRegistry registry) {
        this.registry = registry;
    }

    public void execute(List<String> pluginOrder, MessageContext context) {

        for (String pluginName : pluginOrder) {
            MiddlewarePlugin plugin = registry.getPlugin(pluginName);

            if (plugin == null) {
                throw new RuntimeException("Plugin not found: " + pluginName);
            }

            try {
                plugin.execute(context);
            } catch (Exception ex) {
                context.setStatus("FAILED");
                context.getMetadata().put("error", ex.getMessage());
                throw ex;
            }
        }

        context.setStatus("SUCCESS");
    }
}
