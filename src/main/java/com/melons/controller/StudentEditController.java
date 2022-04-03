package com.melons.controller;

import com.melons.mapper.StudentMapper;
import com.melons.model.StudentModel;
import com.melons.pojo.Student;
import com.melons.service.RoomServiceImpl;
import com.melons.view.StudentEditView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class StudentEditController implements Initializable {
    @FXML
    private TextField numberField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField buildField;
    @FXML
    private TextField oneField;
    @FXML
    private TextField twoField;
    @FXML
    private TextField threeField;
    @FXML
    private  TextField phone;
    @FXML
    private Button cancelButton;
    @FXML
    private CheckBox sex1;
    @FXML
    private CheckBox sex2;

    @Autowired
    private StudentMapper studentMapper;

    @FXML
    private TextField sex;


    public static StudentModel studentModel = new StudentModel();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(studentModel);
        studentModel.numberProperty().addListener(((obs, oldText, newText) -> numberField.setText(newText)));
        studentModel.nameProperty().addListener(((obs, oldText, newText) -> nameField.setText(newText)));
        studentModel.sexProperty().addListener(((obs, oldText, newText) -> sex.setText(newText)));
        studentModel.deptProperty().addListener(((obs, oldText, newText) -> oneField.setText(newText)));
        studentModel.gradeProperty().addListener(((obs, oldText, newText) -> twoField.setText(newText)));
        studentModel.classesProperty().addListener(((obs, oldText, newText) -> threeField.setText(newText)));
        studentModel.phoneProperty().addListener(((obs, oldText, newText) -> phone.setText(newText)));
        numberField.setEditable(false);
    }


    public void mod(){
        studentMapper.update(new Student(numberField.getText(), nameField.getText(), sex.getText(), oneField.getText(), twoField.getText(),threeField.getText(),null,null,phone.getText()));
        clean();
        cancel();
    }


    public void cancel(){
        ((Stage) cancelButton.getScene().getWindow()).close();
        numberField.clear();
    }

    public void clean(){
        numberField.clear();
        nameField.clear();
        oneField.clear();
        twoField.clear();
        threeField.clear();
        phone.clear();
        sex.clear();
    }

}
