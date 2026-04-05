package com.example.demo.service;

import com.example.demo.model.MessageContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiddlewareService {

    private final PluginExecutor executor;

    public MiddlewareService(PluginExecutor executor) {
        this.executor = executor;
    }

    public MessageContext process(String payload, String txnId) {

        MessageContext context = new MessageContext(payload, txnId);

        List<String> flow = List.of(
                "VALIDATION",
                "ENRICHMENT",
                "ROUTING"
        );

        executor.execute(flow, context);

        return context;
    }
}
