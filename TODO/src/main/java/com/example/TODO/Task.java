package com.example.TODO;

import java.time.LocalDateTime;
import java.util.List;

public class Task {
    private final int id;
    private String description;
    private LocalDateTime taskStartDate;
    private LocalDateTime deadline;
    private List<String> tags;
    private boolean completed;

    public Task(int id, String description, LocalDateTime taskStartDate, LocalDateTime deadline, List<String> tags) {
        this.id = id;
        this.description = description;
        this.taskStartDate= taskStartDate;
        this.deadline = deadline;
        this.tags = tags;
        this.completed = false;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getTaskStartDate() {
        return taskStartDate;
    }

    public void setTaskStartDate(LocalDateTime taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    public LocalDateTime getDeadline() { return deadline; }
    public void setDeadline(LocalDateTime deadline) { this.deadline = deadline; }
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
    public boolean isCompleted() { return completed; }
    public void complete() { this.completed = true; }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", tags=" + tags +
                ", completed=" + completed +
                '}'+'\n';
    }
}
