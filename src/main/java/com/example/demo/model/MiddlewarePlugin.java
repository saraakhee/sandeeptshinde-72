package com.example.demo.model;

public interface MiddlewarePlugin {
    void execute(MessageContext context);
    String getName();
}
