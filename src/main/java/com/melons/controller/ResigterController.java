package com.melons.controller;

import com.melons.mapper.UserMapper;
import com.melons.pojo.User;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.FXMLView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.timer.Timer;

@FXMLController
public class ResigterController {
    @FXML
    private Button cancelButton;

    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField confirmTextField;

    @FXML
    private Text loginAlert;

    @FXML
    private Button signButton;

    @Autowired
    private UserMapper userMapper;

    double oldStageX = 0;
    double oldStageY = 0;
    double oldScreenX = 0;
    double oldScreenY = 0;

    public void cancelButtonAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        loginAlert.setText("");
        stage.close();
    }

    public void signButtonAction(ActionEvent event) throws InterruptedException {
        String email = userNameTextField.getText();
        String pwd = passwordTextField.getText();
        String conf = confirmTextField.getText();

        if (email.isEmpty() || !email.matches("^\\w+@(\\w+\\.){1,2}\\w+$")) {
            loginAlert.setText("Please enter the correct Email.");
        } else if (pwd.isEmpty() || conf.isEmpty() || !pwd.equals(conf)) {
            loginAlert.setText("The passwords do not match.");
        } else if(userMapper.loginUser(new User(email,pwd)) != null ){
            loginAlert.setText("User already exists");
        } else {
            userMapper.resigter(new User(email,pwd));
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            userNameTextField.clear();
            passwordTextField.clear();
            confirmTextField.clear();
            loginAlert.setText("");
            stage.close();
        }
    }

    public void handle(MouseEvent e) {
        Window window = userNameTextField.getScene().getWindow();
        if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {    //鼠标按下的事件
            oldStageX = window.getX();
            oldStageY = window.getY();
            oldScreenX = e.getScreenX();
            oldScreenY = e.getScreenY();

        } else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {  //鼠标拖动的事件
            window.setX(e.getScreenX() - oldScreenX + oldStageX);
            window.setY(e.getScreenY() - oldScreenY + oldStageY);
        }
    }

}
