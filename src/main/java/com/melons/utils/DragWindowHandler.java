package com.melons.utils;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.logging.Handler;

public class DragWindowHandler{

    Window stage;
    MouseEvent e;
    double oldStageX ;
    double oldStageY ;
    double oldScreenX ;
    double oldScreenY = 0;

    public DragWindowHandler(){ }
    
    public DragWindowHandler(Window stage, MouseEvent e){
        this.stage = stage;
        this.e = e;
    }

    public void handler(){
        if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {    //鼠标按下的事件
            oldStageX = stage.getX();
            oldStageY = stage.getY();
            oldScreenX = e.getScreenX();
            oldScreenY = e.getScreenY();

        } else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {  //鼠标拖动的事件
            stage.setX(e.getScreenX() - oldScreenX + oldStageX);
            stage.setY(e.getScreenY() - oldScreenY + oldStageY);
        }
    }


//    private Stage primaryStage;
//    private double oldStageX;
//    private double oldStageY;
//    private double oldScreenX;
//    private double oldScreenY;
//
//    public DragWindowHandler(Stage primaryStage) {
//        this.primaryStage = primaryStage;
//    }
//
//    @Override
//    public void handle(MouseEvent e) {
//        if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {    //鼠标按下的事件
//            this.oldStageX = this.primaryStage.getX();
//            this.oldStageY = this.primaryStage.getY();
//            this.oldScreenX = e.getScreenX();
//            this.oldScreenY = e.getScreenY();
//
//        } else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {  //鼠标拖动的事件
//            this.primaryStage.setX(e.getScreenX() - this.oldScreenX + this.oldStageX);
//            this.primaryStage.setY(e.getScreenY() - this.oldScreenY + this.oldStageY);
//        }
//    }
}
