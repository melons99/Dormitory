package com.melons.controller;

import com.melons.mapper.StudentMapper;
import com.melons.model.StudentModel;
import com.melons.pojo.Student;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class StudentAddController {
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


    public void add(){
        if(numberField.getText().isEmpty()||nameField.getText().isEmpty()||oneField.getText().isEmpty()||twoField.getText().isEmpty()||threeField.getText().isEmpty()||phone.getText().isEmpty()||!    (sex1.isSelected()||sex2.isSelected())){
            Alert info1 = new Alert(AlertType.INFORMATION,"请将信息填写完整!");
            info1.showAndWait();
            clean();
        }
        else if(!studentMapper.selectByNumber(numberField.getText()).isEmpty()){

            Alert info2 = new Alert(AlertType.INFORMATION,"该学生已存在!");
            info2.showAndWait();
            clean();

        }
        else {
            String sex = sex1.isSelected()? "男":"女";
            studentMapper.insert(new Student(numberField.getText(), nameField.getText(), sex, oneField.getText(), twoField.getText(),threeField.getText(),null,null,phone.getText()));
            cancel();
        }

    }

    public void cancel(){
        clean();
        ((Stage) cancelButton.getScene().getWindow()).close();
    }

    public void clean(){
        numberField.clear();
        nameField.clear();
        oneField.clear();
        twoField.clear();
        threeField.clear();
        phone.clear();
        sex1.setSelected(false);
        sex2.setSelected(false);
    }
}
