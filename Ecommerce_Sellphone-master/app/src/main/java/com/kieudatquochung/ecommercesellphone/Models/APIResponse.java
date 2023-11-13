package com.kieudatquochung.ecommercesellphone.Models;

public class APIResponse {
    private String message,timestamp;
    private boolean success;

    public APIResponse(String message, String timestamp, boolean success) {
        this.message = message;
        this.timestamp = timestamp;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
