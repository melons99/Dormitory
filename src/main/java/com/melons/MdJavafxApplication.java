package com.melons;

import com.melons.view.ResigterView;
import de.felixroske.jfxsupport.AbstractFxmlView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class MdJavafxApplication extends Application {


//
//    /**
//     * 启动入口
//     *
//     * @param args 启动参数
//     */
//    public static void main(String[] args) {
//        SpringApplication app = new SpringApplication(DemoApplication.class);
//        app.run(args);
////        SpringApplication.run(DemoApplication.class, args);//原来的启动方法
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("桌面应用");
//        Application.launch(Main.class, args);
//


    public static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(MdJavafxApplication.class, args);
        Application.launch(args);
//        launch(MdJavafxApplication.class,loginView.class,args);
    }


//    @Override
//    public void start(Stage stage) throws Exception {
//        super.start(stage);
//        stage.initStyle(StageStyle.TRANSPARENT);
//        stage.setResizable(false);

//        EventHandler handler = new DragWindowHandler(stage);


////        URL url = new URL("/login.fxml");
//        Parent load = FXMLLoader.load(MdJavafxApplication.class.getResource("/login.fxml"));
////        Parent load = FXMLLoader.load(url);
////        AnchorPane load=new AnchorPane();
//        Scene scene = new Scene(load, 600, 400);
//


//        loginTop.setOnMousePressed(handler);
//        loginTop.setOnMouseDragged(handler);

//        stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setResizable(false);
//        stage.show();
//    }

    //
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(root(), 600, 420);

//        EventHandler handler = new DragWindowHandler(primaryStage);
//        scene.setOnMousePressed(handler);
//        scene.setOnMouseDragged(handler);


        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    private Parent root() throws IOException {
        //要将controller注册到工厂，否则autowired注入为NUll
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MdJavafxApplication.class.getResource("/fxml/login.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        return loader.load();
    }

    public static void showView(final Class<? extends AbstractFxmlView> window, final Modality mode) {
        final AbstractFxmlView view = applicationContext.getBean(window);
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
        newStage.initModality(mode);
//        newStage.initOwner(getStage());
//        newStage.setTitle(view.getDefaultTitle());
//        newStage.initStyle(view.getDefaultStyle());

        newStage.showAndWait();
    }

}
