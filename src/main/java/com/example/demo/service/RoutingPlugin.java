package com.example.demo.service;

import com.example.demo.model.MessageContext;
import com.example.demo.model.MiddlewarePlugin;
import org.springframework.stereotype.Component;

@Component
public class RoutingPlugin implements MiddlewarePlugin {

    @Override
    public void execute(MessageContext context) {
        // Example logic
        context.getMetadata().put("route", "RTGS");
    }
    @Override
    public String getName() {
        return "ROUTING";
    }
}
