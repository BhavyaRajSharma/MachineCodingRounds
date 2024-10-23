package com.example.TODO;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class TaskManager {
    private final Map<Integer, Task> taskMap = new ConcurrentHashMap<>();
    private final List<ActivityLogEntry> activityLog = new CopyOnWriteArrayList<>();
    private int taskIdCounter = 1;

    public void addTask(String description, LocalDateTime taskStartDate, LocalDateTime deadline, List<String> tags) {
        Task task = new Task(taskIdCounter++, description, taskStartDate, deadline, tags);
        taskMap.put(task.getId(), task);
        activityLog.add(new ActivityLogEntry("Added task: " + task.getDescription(), LocalDateTime.now()));
    }

    public Task getTask(int taskId) {
        return taskMap.get(taskId);
    }

    public void modifyTask(int taskId, String description, LocalDateTime taskStartDate, LocalDateTime deadline, List<String> tags) {
        Task task = getTask(taskId);
        if (task != null) {
            synchronized (task) {
                task.setDescription(description);
                task.setTaskStartDate(taskStartDate);
                task.setDeadline(deadline);
                task.setTags(tags);
                activityLog.add(new ActivityLogEntry("Modified task ID: " + taskId, LocalDateTime.now()));
            }
        }
    }

    public void removeTask(int taskId) {
        Task task = getTask(taskId);
        if (task != null) {
            taskMap.remove(taskId);
            activityLog.add(new ActivityLogEntry("Removed task ID: " + taskId, LocalDateTime.now()));
        }
    }

    public void completeTask(int taskId) {
        Task task = getTask(taskId);
        if (task != null) {
            synchronized (task) {
                task.complete();
//                removeTask(taskId);
                activityLog.add(new ActivityLogEntry("Completed task ID: " + taskId, LocalDateTime.now()));
            }
        }
    }

    public List<Task> listTasks(SortBy sortBy, boolean ascending) {
        List<Task> tasks = new ArrayList<>(taskMap.values());
        tasks = tasks.stream()
                .filter(t -> !t.isCompleted())
                .filter(t -> t.getTaskStartDate().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());

        // Sorting logic
        Comparator<Task> comparator;
        switch (sortBy) {
            case DESCRIPTION:
                comparator = Comparator.comparing(Task::getDescription);
                break;
            case DEADLINE:
                comparator = Comparator.comparing(Task::getDeadline);
                break;
            case COMPLETED:
                comparator = Comparator.comparing(Task::isCompleted);
                break;
            default:
                comparator = Comparator.comparing(Task::getId); // Default sorting by task ID
        }

        if (!ascending) {
            comparator = comparator.reversed();
        }

        return tasks.stream().sorted(comparator).collect(Collectors.toList());
    }

    public List<ActivityLogEntry> getActivityLog(LocalDateTime startDate, LocalDateTime endDate) {
        Optional<LocalDateTime> optionalStartDate = Optional.ofNullable(startDate);
        Optional<LocalDateTime> optionalEndDate = Optional.ofNullable(endDate);

        return activityLog.stream()
                .filter(entry ->
                        (optionalStartDate.isEmpty() || entry.getTimestamp().isAfter(optionalStartDate.get())) &&
                                (optionalEndDate.isEmpty() || entry.getTimestamp().isBefore(optionalEndDate.get()))
                )
                .collect(Collectors.toList());
    }

    public Map<String, Integer> getStatistics(LocalDateTime startDate, LocalDateTime endDate) {
        Optional<LocalDateTime> optionalStartDate = Optional.ofNullable(startDate);
        Optional<LocalDateTime> optionalEndDate = Optional.ofNullable(endDate);

        List<Task> filteredTasks = taskMap.values().stream()
                .filter(t ->
                        (optionalStartDate.isEmpty() || t.getDeadline().isAfter(optionalStartDate.get())) &&
                                (optionalEndDate.isEmpty() || t.getDeadline().isBefore(optionalEndDate.get()))
                )
                .filter(t -> t.getTaskStartDate().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());

        int totalTasks = filteredTasks.size();
        long completedTasks = filteredTasks.stream().filter(Task::isCompleted).count();
        long spilledOverTasks = filteredTasks.stream()
                .filter(t -> t.getDeadline().isBefore(LocalDateTime.now())).count();

        Map<String, Integer> stats = new HashMap<>();
        stats.put("Total Tasks", totalTasks);
        stats.put("Completed Tasks", (int) completedTasks);
        stats.put("Spilled Over Tasks", (int) spilledOverTasks);
        return stats;
    }
}
