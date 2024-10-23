package com.example.TODO;

import java.time.LocalDateTime;

public class ActivityLogEntry {
    private final String message;
    private final LocalDateTime timestamp;

    public ActivityLogEntry(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "ActivityLogEntry{" +
                "message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}'+'\n';
    }
}
