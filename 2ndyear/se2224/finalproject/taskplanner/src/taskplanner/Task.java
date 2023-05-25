package taskplanner;

import java.time.LocalDate;
public class Task {
    private Integer id;
    private String name;
    private String shortDescription;
    private LocalDate deadline;
    private Integer priority;
    private boolean reminderImageOn;

    Task(Integer id, String name, String shortDescription, LocalDate deadline, Integer priority, boolean reminderImageOn) {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
        this.deadline = deadline;
        this.priority = priority;
        this.reminderImageOn = reminderImageOn;
    }

    Task(String name, String shortDescription, LocalDate deadline, Integer priority, boolean reminderImageOn) {
        this(null, name, shortDescription, deadline, priority, reminderImageOn);
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

    void setId(Integer id) {
        this.id = id;
    }

    Integer getId() {
        return id;
    }
}
