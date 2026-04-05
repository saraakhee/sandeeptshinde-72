package com.example.demo.service;

import com.example.demo.model.MessageContext;
import com.example.demo.model.MiddlewarePlugin;
import org.springframework.stereotype.Component;

@Component
public class EnrichmentPlugin implements MiddlewarePlugin {

    @Override
    public void execute(MessageContext context) {
        context.getMetadata().put("enriched", "YES");
    }

    @Override
    public String getName() {
        return "ENRICHMENT";
    }
}
