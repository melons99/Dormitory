package com.melons.controller;

import com.melons.mapper.OutsidersMapper;
import com.melons.pojo.Outsiders;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@FXMLController
public class OutsidersAddController {
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private TextField reason;
    @FXML
    private TextField text;
    @FXML
    private Button cancelbutton;
    @Autowired
    private OutsidersMapper outsidersMapper;

    public void add() {

        java.util.Date date = new java.util.Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        outsidersMapper.insert(new Outsiders(name.getText(),phone.getText(),reason.getText(),timeStamp,text.getText()));
        cancel();
    }

    public void clean(){
        name.clear();
        phone.clear();
        reason.clear();
        text.clear();
    }

    public void cancel() {
        clean();
        ((Stage) cancelbutton.getScene().getWindow()).close();
    }




}
