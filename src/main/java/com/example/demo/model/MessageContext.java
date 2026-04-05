package com.example.demo.model;


import java.util.HashMap;
import java.util.Map;

public class MessageContext {

    private String payload;
    private String transactionId;
    private String status;
    private Map<String, Object> metadata = new HashMap<>();

    public MessageContext(String payload, String transactionId) {
        this.payload = payload;
        this.transactionId = transactionId;
        this.status = "INITIATED";
    }

    public String getPayload() { return payload; }
    public void setPayload(String payload) { this.payload = payload; }

    public String getTransactionId() { return transactionId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Map<String, Object> getMetadata() { return metadata; }
}
