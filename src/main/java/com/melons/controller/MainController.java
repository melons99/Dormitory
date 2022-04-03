package com.melons.controller;

import com.melons.MdJavafxApplication;
import com.melons.mapper.*;
import com.melons.model.StudentModel;
import com.melons.pojo.*;
import com.melons.view.*;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

@FXMLController
public class MainController implements Initializable {
    @FXML
    private TableView<Student> stuview;
    @FXML
    private  TableView<Build> roomview1;
    @FXML
    private  TableView<Room> roomview2;
    @FXML
    private TableColumn<Student, String> number;
    @FXML
    private TableColumn<Student, String> name;
    @FXML
    private TableColumn<Student, String> sex;
    @FXML
    private TableColumn<Student, String> dept;
    @FXML
    private TableColumn<Student, String> grade;
    @FXML
    private TableColumn<Student, String> classes;
    @FXML
    private TableColumn<Student, String> building;
    @FXML
    private TableColumn<Student, String> room;
    @FXML
    private TableColumn<Student, String> phone;
    @FXML
    private TableColumn<Build,String> build1;
    @FXML
    private TableColumn<Build,String> text1;
    @FXML
    private TableColumn<Build,String> build2;
    @FXML
    private TableColumn<Build,String> room2;
    @FXML
    private TableColumn<Build,String> roomer;
    @FXML
    private TableColumn<Build,String> text2;
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
    private TextField buildField1;
    @FXML
    private TextField roomField1;
    @FXML
    private TableColumn<Outsiders,String> no1;
    @FXML
    private TableColumn<Outsiders,String> name1;
    @FXML
    private TableColumn<Outsiders,String> phone1;
    @FXML
    private TableColumn<Outsiders,String> reason1;
    @FXML
    private TableColumn<Outsiders,String> entrytime1;
    @FXML
    private TableColumn<Outsiders,String> leavetime1;
    @FXML
    private TableColumn<Outsiders,String> text3;
    @FXML
    private TableColumn<Goods,String> no2;
    @FXML
    private TableColumn<Goods,String> number2;
    @FXML
    private TableColumn<Goods,String> name2;
    @FXML
    private TableColumn<Goods,String> phone2;
    @FXML
    private TableColumn<Goods,String> goodstext2;
    @FXML
    private TableColumn<Goods,String> outtime2;
    @FXML
    private TableColumn<Goods,String> text4;
    @FXML
    private TableView<Outsiders> outview;
    @FXML
    private  TableView<Goods> goodview;

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private BuildMapper buildMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private OutsidersMapper outsidersMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void findAll() {
        showTable(getData("all"));
        numberField.setText("");
        nameField.setText("");
        buildField.setText("");
        oneField.setText("");
        twoField.setText("");
        threeField.setText("");
    }

    public void selectByNumber() {
        showTable((getData("num")));
        nameField.setText("");
        buildField.setText("");
        oneField.setText("");
        twoField.setText("");
        threeField.setText("");
    }

    public void selectByName() {
        showTable((getData("name")));
        numberField.setText("");
        buildField.setText("");
        oneField.setText("");
        twoField.setText("");
        threeField.setText("");
    }

    public void selectByBuild() {
        showTable((getData("build")));
        numberField.setText("");
        nameField.setText("");
        oneField.setText("");
        twoField.setText("");
        threeField.setText("");
    }

    public void selectByThree() {
        showTable((getData("three")));
        numberField.setText("");
        nameField.setText("");
        buildField.setText("");
    }

    public void addStudent() {
        win2();
        findAll();
    }

    public void modStudent() {
        Student s = stuview.getSelectionModel().selectedItemProperty().get();
        StudentModel studentModel = new StudentModel(s.getNumber(), s.getName(), s.getSex(), s.getDept(), s.getGrade(), s.getClasses(), s.getBuildingNo(), s.getRoomNo(), s.getPhone());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("studentEdit.fxml"));
            StudentEditController studentEditController = loader.getController();
            studentEditController.studentModel.setNumber(s.getNumber());
            studentEditController.studentModel.setName(s.getName());
            studentEditController.studentModel.setSex(s.getSex());
            studentEditController.studentModel.setDept(s.getDept());
            studentEditController.studentModel.setGrade(s.getGrade());
            studentEditController.studentModel.setClasses(s.getClasses());
            studentEditController.studentModel.setPhone(s.getPhone());
        } catch (Exception e) {
            e.printStackTrace();
        }
        win();
        findAll();

    }

    public void deleteStudent() {
        Student s = stuview.getSelectionModel().selectedItemProperty().get();
        System.out.println(s.getNumber());

        try {
            studentMapper.deleteById(s.getNumber());
            roomMapper.del(s.getBuildingNo(), s.getRoomNo());
            Alert info = new Alert(Alert.AlertType.INFORMATION, "删除成功!");
            info.showAndWait();
            findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void selectByRoom(){
        List<Room> roomList = roomMapper.selectBySet(buildField1.getText(),roomField1.getText());
        ObservableList<Room> rooms = FXCollections.observableArrayList();
        for (Room room : roomList) {
//            System.out.println(room);
            rooms.add(room);

        }
        build2.setCellValueFactory(new PropertyValueFactory<>("buildname"));
        room2.setCellValueFactory(new PropertyValueFactory<>("roomname"));
        roomer.setCellValueFactory(new PropertyValueFactory<>("count"));
        text2.setCellValueFactory(new PropertyValueFactory<>("roomtext"));
        roomview2.setItems(rooms);
        buildField1.clear();
        roomField1.clear();

    }

    public void addRoom() {
        win4();
        selectRoom();
    }

    public void deleteRoom() {
        Room r = roomview2.getSelectionModel().selectedItemProperty().get();
        try {
            if(r.getCount() != 0){
                Alert info = new Alert(Alert.AlertType.INFORMATION, "该房间有人入住,无法删除!");
                info.showAndWait();
            }else {
                Map map = new HashMap();
                map.put("buildname",r.getBuildname());
                map.put("roomname",r.getRoomname());
                roomMapper.delete(map);
                Alert info1 = new Alert(Alert.AlertType.INFORMATION, "删除成功!");
                info1.showAndWait();
                selectRoom();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectBuild() {
        List<Build> buildList;
        buildList = buildMapper.findAllBuilds();

        ObservableList<Build> builds = FXCollections.observableArrayList();
        for (Build build : buildList) {
            builds.add(build);

        }
        build1.setCellValueFactory(new PropertyValueFactory<>("buildname"));
        text1.setCellValueFactory(new PropertyValueFactory<>("buildtext"));
        roomview1.setItems(builds);

    }

    public void selectRoom(){
        List<Room> roomList;
        roomList = roomMapper.findAllRooms();

        ObservableList<Room> rooms = FXCollections.observableArrayList();
        for (Room room : roomList) {
            rooms.add(room);

        }
        build2.setCellValueFactory(new PropertyValueFactory<>("buildname"));
        room2.setCellValueFactory(new PropertyValueFactory<>("roomname"));
        roomer.setCellValueFactory(new PropertyValueFactory<>("count"));
        text2.setCellValueFactory(new PropertyValueFactory<>("roomtext"));
        roomview2.setItems(rooms);
    }
    
    public void addBuild() {
        win3();
        selectBuild();
    }

    public void deleteBuild() {
        Build b = roomview1.getSelectionModel().selectedItemProperty().get();

        try {
            if(roomMapper.findByBuild(b.getBuildname()).isEmpty()){
                buildMapper.delete(b.getBuildname());
                Alert info = new Alert(Alert.AlertType.INFORMATION, "删除成功!");
                info.showAndWait();
                selectBuild();
            }
            else {
                Alert info = new Alert(Alert.AlertType.INFORMATION, "该楼栋有房间,无法删除！!");
                info.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void roomSet(){
        Student s = stuview.getSelectionModel().selectedItemProperty().get();
        StudentModel studentModel = new StudentModel(s.getNumber(), s.getName());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("roomSet.fxml"));
        RoomSetController roomSetController = loader.getController();
        roomSetController.studentModel.setNumber(s.getNumber());
        roomSetController.studentModel.setName(s.getName());
        win5();
        if(!stuview.getItems().isEmpty()){
            findAll();
        }

    }
    public void log1(){

    }

    public void log2(){
        selectBuild();
        selectRoom();
        if(!stuview.getItems().isEmpty()){
            findAll();
        }
    }

    public void log3(){
        findOut();
        findGoods();
    }

    public void add1(){
        win6();
        findOut();
    }

    public void out1(){
        Outsiders o = outview.getSelectionModel().selectedItemProperty().get();
        Map map = new HashMap();
        java.util.Date date = new java.util.Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        map.put("no",o.getNo());
        map.put("leavetime",timeStamp);
        outsidersMapper.update(map);
        findOut();
    }

    public void add2(){
        win7();
        findGoods();
    }

    public void del2(){
        Goods b = goodview.getSelectionModel().selectedItemProperty().get();

        try {
            goodsMapper.delete(b.getNo());
            Alert info = new Alert(Alert.AlertType.INFORMATION, "删除成功!");
            info.showAndWait();
            findGoods();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findOut(){

        List<Outsiders> outsidersList = outsidersMapper.findAll();


        ObservableList<Outsiders> outsiders = FXCollections.observableArrayList();
        for (Outsiders outsider : outsidersList) {
            System.out.println(outsider);
            outsiders.add(outsider);
        }
        no1.setCellValueFactory(new PropertyValueFactory<>("no"));
        name1.setCellValueFactory(new PropertyValueFactory<>("name"));
        phone1.setCellValueFactory(new PropertyValueFactory<>("phone"));
        reason1.setCellValueFactory(new PropertyValueFactory<>("reason"));
        entrytime1.setCellValueFactory(new PropertyValueFactory<>("entrytime"));
        leavetime1.setCellValueFactory(new PropertyValueFactory<>("leavetime"));
        text3.setCellValueFactory(new PropertyValueFactory<>("text"));

        outview.setItems(outsiders);
    }

    public void findGoods(){
        List<Goods> goodsList = goodsMapper.findAll();

        ObservableList<Goods> goods = FXCollections.observableArrayList();
        for (Goods good : goodsList) {
            goods.add(good);
        }
        no2.setCellValueFactory(new PropertyValueFactory<>("no"));
        name2.setCellValueFactory(new PropertyValueFactory<>("name"));
        number2.setCellValueFactory(new PropertyValueFactory<>("number"));
        phone2.setCellValueFactory(new PropertyValueFactory<>("phone"));
        goodstext2.setCellValueFactory(new PropertyValueFactory<>("info"));
        text4.setCellValueFactory(new PropertyValueFactory<>("text"));
        outtime2.setCellValueFactory(new PropertyValueFactory<>("time"));
        goodview.setItems(goods);
    }

    public ObservableList<Student> getData(String flag) {

        List<Student> lists = new ArrayList<>();

        switch (flag) {
            case "build":
                lists = studentMapper.selectByBuildingNo(buildField.getText());
                break;
            case "name":
                lists = studentMapper.selectByName(nameField.getText());
                break;
            case "num":
                lists = studentMapper.selectByNumber(numberField.getText());
                break;
            case "all":
                lists = studentMapper.findAll();
                break;
            case "three":
                lists = studentMapper.selectBySet(oneField.getText(), twoField.getText(), threeField.getText());
                break;

        }


        ObservableList<Student> students = FXCollections.observableArrayList();
        //进行遍历 并加载到students中
        for (Student list : lists) {
//            System.out.println(list);
            students.add(list);
        }
        return students;
    }

    public void showTable(ObservableList<Student> students) {
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        dept.setCellValueFactory(new PropertyValueFactory<>("dept"));
        grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        classes.setCellValueFactory(new PropertyValueFactory<>("classes"));
        building.setCellValueFactory(new PropertyValueFactory<>("buildingNo"));
        room.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        stuview.setItems(students);
    }

    public void win() {

        AbstractFxmlView view = MdJavafxApplication.applicationContext.getBean(StudentEditView.class);

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
        newStage.setTitle("修改用户");
//        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.showAndWait();
    }

    public void win2() {
        AbstractFxmlView view = MdJavafxApplication.applicationContext.getBean(StudentAddView.class);
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
        newStage.setTitle("添加用户");
//        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.showAndWait();
    }

    public void win3(){
        AbstractFxmlView view = MdJavafxApplication.applicationContext.getBean(BuildAddView.class);
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
        newStage.setTitle("添加楼栋");
//        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.showAndWait();
    }

    public void win4(){
        AbstractFxmlView view = MdJavafxApplication.applicationContext.getBean(RoomAddView.class);
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
        newStage.setTitle("添加房间");
//        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.showAndWait();
    }

    public void win5(){
        AbstractFxmlView view = MdJavafxApplication.applicationContext.getBean(RoomSetView.class);
        Stage newStage = new Stage();
        Scene newScene;
        if (view.getView().getScene() != null) {
            // This view was already shown so
            // we have a scene for it and use this one.
            newScene = view.getView().getScene();
        } else {
            newScene = new Scene(view.getView());
        }
        System.out.println("456");
        newStage.setScene(newScene);
        newStage.setTitle("寝室分配");
//        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.showAndWait();
    }

    public void win6(){
        AbstractFxmlView view = MdJavafxApplication.applicationContext.getBean(OutsidersAddView.class);
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
        newStage.setTitle("外来人员登记");
//        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.showAndWait();
    }

    public void win7(){
        AbstractFxmlView view = MdJavafxApplication.applicationContext.getBean(GoodsAddView.class);
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
        newStage.setTitle("货物登记");
//        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.showAndWait();
    }
}

