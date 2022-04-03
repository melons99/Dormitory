package com.melons.controller;

import com.melons.mapper.BuildMapper;
import com.melons.mapper.RoomMapper;
import com.melons.model.StudentModel;
import com.melons.pojo.Build;
import com.melons.pojo.Room;
import com.melons.service.RoomServiceImpl;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.FXMLView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@FXMLController
public class RoomSetController implements Initializable {
    @FXML
    private Label number;
    @FXML
    private Label name;
    @FXML
    private ComboBox buildbox;
    @FXML
    private ComboBox roombox;

    @Autowired
    private BuildMapper buildMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private RoomServiceImpl roomServiceImpl;

    public static StudentModel studentModel = new StudentModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentModel.numberProperty().addListener(((obs, oldText, newText) -> number.setText(newText)));
        studentModel.nameProperty().addListener(((obs, oldText, newText) -> name.setText(newText)));
    }


    public void set(){
        System.out.println(number.getText()+"fuck");
        if (roomMapper.findByName(buildbox.getValue().toString(),roombox.getValue().toString()).getCount()>=4){
            Alert.AlertType alertAlertType;
            Alert info1 = new Alert(Alert.AlertType.INFORMATION,"该宿舍已满人!");
            info1.showAndWait();
        }
        else {
            try {
                roomServiceImpl.update(buildbox.getValue().toString(),roombox.getValue().toString(),number.getText());

            } catch (Exception e) {
                e.printStackTrace();
            }
            cancel();
        }

    }

    public void buildlist(){

        List<Build> buildList;
        buildList = buildMapper.findAllBuilds();

        ObservableList<String> builds = FXCollections.observableArrayList();
        for (Build build : buildList) {
//            System.out.println(build);
            builds.add(build.getBuildname());
        }
        buildbox.setItems(builds);
    }

    public void roomlist(){
        System.out.println(buildbox.getValue().toString());
        List<Room> roomList = roomMapper.findByBuild(buildbox.getValue().toString());
        ObservableList<String> rooms = FXCollections.observableArrayList();
        for (Room room : roomList) {
//            System.out.println(room);
            rooms.add(room.getRoomname());
        }
        roombox.setItems(rooms);
    }




    public void cancel(){
        clean();
        ((Stage) buildbox.getScene().getWindow()).close();
    }
    public void clean(){
        buildbox.getSelectionModel().select(-1);
        roombox.getSelectionModel().select(-1);
        number.setText("");
        name.setText("");
    }



}
