package taskplanner;

import javax.swing.*;

public class InsertTaskFrame extends SubFrame {
    InsertTaskFrame(JFrame parent) {
        super("Insert A Task", 600, 500, parent);
    }
    public void main() {
        super.main();
        addInputBoxWithLabel("Task Name", 200, 50);
        addTextAreaWithLabel("Short Description", 200, 100, 300, 45, 100);
        addDatePickerWithLabel("Deadline", 200, 180);
    }
}
