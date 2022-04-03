package com.melons.model;

import javafx.beans.property.SimpleStringProperty;
import lombok.Data;

@Data
public class StudentModel {

    private final SimpleStringProperty number;
    private final SimpleStringProperty name;
    private final SimpleStringProperty sex;
    private final SimpleStringProperty dept;
    private final SimpleStringProperty grade;
    private final SimpleStringProperty classes;
    private final SimpleStringProperty buildingNo;
    private final SimpleStringProperty roomNo;
    private final SimpleStringProperty phone;

    public StudentModel() {
        this(null,null,null,null,null,null,null,null,null);
    }

    public StudentModel(String number, String name, String sex, String dept, String grade, String classes, String buildingNo, String roomNo, String phone) {
        this.number = new SimpleStringProperty(number);
        this.name = new SimpleStringProperty(name);
        this.sex = new SimpleStringProperty(sex);
        this.dept = new SimpleStringProperty(dept);
        this.grade = new SimpleStringProperty(grade);
        this.classes = new SimpleStringProperty(classes);
        this.buildingNo = new SimpleStringProperty(buildingNo);
        this.roomNo = new SimpleStringProperty(roomNo);
        this.phone = new SimpleStringProperty(phone);
    }

    public StudentModel(String number,String name){
        this(number,name,null,null,null,null,null,null,null);
    }

    public String getNumber() {
        return number.get();
    }

    public SimpleStringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSex() {
        return sex.get();
    }

    public SimpleStringProperty sexProperty() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }

    public String getDept() {
        return dept.get();
    }

    public SimpleStringProperty deptProperty() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept.set(dept);
    }

    public String getGrade() {
        return grade.get();
    }

    public SimpleStringProperty gradeProperty() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade.set(grade);
    }

    public String getClasses() {
        return classes.get();
    }

    public SimpleStringProperty classesProperty() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes.set(classes);
    }

    public String getBuildingNo() {
        return buildingNo.get();
    }

    public SimpleStringProperty buildingNoProperty() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo.set(buildingNo);
    }

    public String getRoomNo() {
        return roomNo.get();
    }

    public SimpleStringProperty roomNoProperty() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo.set(roomNo);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }
}
