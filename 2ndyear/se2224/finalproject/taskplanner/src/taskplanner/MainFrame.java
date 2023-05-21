package taskplanner;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
public class MainFrame extends SubFrame {
    MainFrame(JFrame parent) {
        super("TaskPlanner", 600, 500, parent);
    }
    public void main() {
        super.main();
        addButton("Insert a task", 200, 20, 200, 60, (ActionEvent e) -> {
            InsertTaskFrame insertTaskFrame = new InsertTaskFrame(this);
            insertTaskFrame.init();
        });
        addButton("Show all task", 200, 20, 200, 60, (ActionEvent e) -> {

        });
    }
}
