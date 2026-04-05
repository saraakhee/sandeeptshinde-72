package com.example.demo.service;

import com.example.demo.model.MessageContext;
import com.example.demo.model.MiddlewarePlugin;
import org.springframework.stereotype.Component;

@Component
public class ValidationPlugin implements MiddlewarePlugin {
    @Override
    public void execute(MessageContext context) {
        if (context.getPayload() == null || context.getPayload().isEmpty()) {
            throw new RuntimeException("Invalid payload");
        }
        context.getMetadata().put("validated", true);
    }

    @Override
    public String getName() {
        return "VALIDATION";
    }
}
