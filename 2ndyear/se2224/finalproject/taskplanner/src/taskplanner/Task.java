package taskplanner;

import java.time.LocalDate;

public class Task {
    private String name;
    private String shortDescription;
    private LocalDate deadline;
    private Integer priority;
    private boolean reminderImageOn;
    Task(String name, String shortDescription, LocalDate deadline, Integer priority, boolean reminderImageOn) {
        setFields(name, shortDescription, deadline, priority, reminderImageOn);
    }

    void setFields(String name, String shortDescription, LocalDate deadline, Integer priority, boolean reminderImageOn) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.deadline = deadline;
        this.priority = priority;
        this.reminderImageOn = reminderImageOn;
    }

    String getName() {
        return name;
    }

    String getShortDescription() {
        return shortDescription;
    }

    LocalDate getDeadline() {
        return deadline;
    }

    Integer getPriority() {
        return priority;
    }

    boolean isReminderImageOn() {
        return reminderImageOn;
    }
}
