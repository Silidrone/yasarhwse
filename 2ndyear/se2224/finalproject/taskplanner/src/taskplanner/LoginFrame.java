package taskplanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends MFrame {
    private Repo repo = new Repo();
    LoginFrame() {
        super("Login",300, 230);
    }

    @Override
    public void main() {
        var usernamePair = addTextFieldWithLabel("Username",75, 20);
        var passwordPair = addPasswordFieldWithLabel("Password", 75, 70);
        var errorLabel = addLabel("Login information wrong!", 65, 120, Color.RED);
        errorLabel.setVisible(false);

        addButton("Login", 100, 150, 100, 30, (ActionEvent e) -> {
            if(repo.checkLogin(usernamePair.getValue().getText(), String.valueOf(passwordPair.getValue().getPassword()))) {
                errorLabel.setVisible(false);
                MainFrame mainFrame = new MainFrame(this);
                mainFrame.init();
            } else {
                errorLabel.setVisible(true);
            }
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
