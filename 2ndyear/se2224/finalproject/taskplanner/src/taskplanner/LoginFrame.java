package taskplanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends MFrame {
    LoginFrame() {
        super("Login",300, 400);
    }
    
    @Override
    public void main() {
//        var usernamePair = addInputBoxWithLabel("Username",75, 20);
//        var passwordPair = addInputBoxWithLabel("Password", 75, 70);
//        var errorLabel = addLabel("Login information wrong!", 55, 170, Color.RED);
//        errorLabel.setVisible(false);
//
//        addButton("Login", 100, 130, 100, 30, (ActionEvent e) -> {
//            if(Repo.getInstance().checkLogin(usernamePair.getValue().getText(), passwordPair.getValue().getText())) {
//                errorLabel.setVisible(false);
//                MainFrame mainFrame = new MainFrame(this);
//                mainFrame.init();
//            } else {
//                errorLabel.setVisible(true);
//            }
//        });
//
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MainFrame mainFrame = new MainFrame(this);
        mainFrame.init();
    }
}
