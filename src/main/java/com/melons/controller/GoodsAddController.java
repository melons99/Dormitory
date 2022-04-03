package com.melons.controller;

import com.melons.mapper.GoodsMapper;
import com.melons.mapper.StudentMapper;
import com.melons.pojo.Goods;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

@FXMLController
public class GoodsAddController {
    @FXML
    private TextField number;
    @FXML
    private TextField info;
    @FXML
    private TextField text;
    @FXML
    private Button cancelbutton;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private StudentMapper studentMapper;

    public void add(){
        if(studentMapper.selectByNumber(number.getText()).isEmpty()){
            Alert.AlertType alertAlertType;
            Alert info1 = new Alert(Alert.AlertType.INFORMATION,"该学生不存在!");
            info1.showAndWait();
        }
        else{
            java.util.Date date = new java.util.Date();
            Timestamp timeStamp = new Timestamp(date.getTime());
            goodsMapper.insert(new Goods(number.getText(),info.getText(),timeStamp,text.getText()));
            cancel();
        }

    }

    public void clean(){
        number.clear();
        info.clear();
        text.clear();
    }

    public void cancel(){
        clean();
        ((Stage) cancelbutton.getScene().getWindow()).close();
    }
}
