package com.melons.controller;

import com.melons.mapper.BuildMapper;
import com.melons.mapper.RoomMapper;
import com.melons.pojo.Build;
import com.melons.pojo.Room;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FXMLController
public class RoomAddController {
    @FXML
    private ComboBox buildbox;
    @FXML
    private TextField roomtext;
    @FXML
    private TextArea text;
    
    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private BuildMapper buildMapper;

    public void add(){
        Map map = new HashMap();
        map.put("buildname",buildbox.getValue().toString());
        map.put("roomname",roomtext.getText());
        if(buildbox.getValue().toString().isEmpty()||roomtext.getText().isEmpty()){
            Alert.AlertType alertAlertType;
            Alert info1 = new Alert(Alert.AlertType.INFORMATION,"请将信息填写完整!");
            info1.showAndWait();
            clean();
        }
        else if(!roomMapper.selectBySet(buildbox.getValue().toString(),roomtext.getText()).isEmpty()){
            Alert info3 = new Alert(Alert.AlertType.INFORMATION,"该房间已存在!");
            info3.showAndWait();
            clean();
        }
        else {
            roomMapper.insert(new Room(buildbox.getValue().toString(),roomtext.getText(),0,text.getText()));
            clean();
            cancel();
        }
    }

    public void buildlist(){
        List<Build> buildList;
        buildList = buildMapper.findAllBuilds();

        ObservableList<String> builds = FXCollections.observableArrayList();
        for (Build build : buildList) {
            System.out.println(build);
            builds.add(build.getBuildname());
        }
        buildbox.setItems(builds);
    }

    public void cancel(){
        clean();
        ((Stage) roomtext.getScene().getWindow()).close();
    }

    public void clean(){
        buildbox.getSelectionModel().select(-1);
        roomtext.clear();
        text.clear();
    }
}
