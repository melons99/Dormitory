package com.melons.controller;

import com.melons.mapper.BuildMapper;
import com.melons.mapper.StudentMapper;
import com.melons.pojo.Build;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

@FXMLController
public class BuildAddController {
    @FXML
    private TextField buildfield;
    @FXML
    private TextArea textfield;

    @Autowired
    private BuildMapper buildMapper;

    public void add(){
        if(buildfield.getText().isEmpty()){
            Alert.AlertType alertAlertType;
            Alert info1 = new Alert(AlertType.INFORMATION,"请填写楼栋!");
            info1.showAndWait();
        }
        else if(!buildMapper.findByName(buildfield.getText()).isEmpty()){
            Alert info2 = new Alert(AlertType.INFORMATION,"该楼栋已存在!");
            info2.showAndWait();
            buildfield.clear();
            textfield.clear();
        }
        else {
            buildMapper.insert(new Build(buildfield.getText(),textfield.getText()));
            cancel();
        }
    }

    public void cancel(){
        buildfield.clear();
        textfield.clear();
        ((Stage) buildfield.getScene().getWindow()).close();
    }
}
