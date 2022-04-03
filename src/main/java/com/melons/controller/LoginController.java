package com.melons.controller;

import com.melons.MdJavafxApplication;
import com.melons.mapper.UserMapper;
import com.melons.pojo.User;
import com.melons.utils.DragWindowHandler;
import com.melons.view.MainView;
import com.melons.view.ResigterView;
import com.melons.view.loginView;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

@FXMLController
public class LoginController implements Initializable{

    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button cancelButton;

    @FXML
    private Text loginAlert;


    @Autowired
    private UserMapper userMapper;

    double oldStageX = 0;
    double oldStageY = 0;
    double oldScreenX = 0;
    double oldScreenY = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        AbstractFxmlView view = MdJavafxApplication.getScene();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(MdJavafxApplication.class.getResource("/login.fxml"));
//        System.out.println(loginpane.getScene());

//        Stage primaryStage = (Stage) userNameTextField.getScene().getWindow();
//        Scene scene = (Scene)userNameTextField.getScene();
//        EventHandler handler = new DragWindowHandler(primaryStage);
//        scene.setOnMousePressed(handler);
//        scene.setOnMouseDragged(handler);
//        primaryStage.setScene(scene);
//        primaryStage.initStyle(StageStyle.UNDECORATED);
//        primaryStage.setResizable(false);
//        primaryStage.show();
    }



    public void loginButtonAction(ActionEvent event){
        if (userMapper.loginUser(new User(userNameTextField.getText(),passwordTextField.getText())) != null) {
            loginAlert.setText("hello"+ userNameTextField.getText());
            openMainWindow();
        }
        else {
            loginAlert.setText("Invalid login.Please try again.");
            passwordTextField.clear();
        }
    }

    public void cancelButtonAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void registerButtonAction(ActionEvent event){
        loginAlert.setText("");
        AbstractFxmlView view = MdJavafxApplication.applicationContext.getBean(ResigterView.class);

//        StackPane sk = new StackPane();
//        sk.getChildren().addAll(view.getView().getParent());

        Stage newStage = new Stage();
        Scene newScene;
        if (view.getView().getScene() != null) {
            // This view was already shown so
            // we have a scene for it and use this one.
            newScene = view.getView().getScene();
        } else {
            newScene = new Scene(view.getView());
        }

        newStage.setScene(newScene);
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.show();


    }

    public void openMainWindow(){
//        Stage mainStage = new Stage();
//        mainStage.show();
//        MdJavafxApplication.showView(loginView.class,Modality.NONE);
        AbstractFxmlView view = MdJavafxApplication.applicationContext.getBean(MainView.class);


        Stage newStage = new Stage();

        Scene newScene;
        if (view.getView().getScene() != null) {
            // This view was already shown so
            // we have a scene for it and use this one.
            newScene = view.getView().getScene();
        } else {
            newScene = new Scene(view.getView());
        }

//        EventHandler handler = new DragWindowHandler(newStage);
//        newScene.setOnMousePressed(handler);
//        newScene.setOnMouseDragged(handler);

        Window window = userNameTextField.getScene().getWindow();
        if(window instanceof Stage){
            ((Stage) window).close();
        }

        newStage.setScene(newScene);
//        newStage.initModality(mode);
//        newStage.initOwner(getStage());
//        newStage.initStyle(StageStyle.UNDECORATED);
//        newStage.setTitle(view.getDefaultTitle());
//        newStage.initStyle(view.getDefaultStyle());
        newStage.setTitle("学生公寓管理系统");
        newStage.showAndWait();

//        Stage stage = (Stage)userNameTextField.getScene().getWindow();
//        stage.setScene(newScene);
//
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
