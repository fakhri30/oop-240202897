package com.upb.agripos;

import com.upb.agripos.view.ProductFormView;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        ProductFormView view = new ProductFormView();
        view.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}