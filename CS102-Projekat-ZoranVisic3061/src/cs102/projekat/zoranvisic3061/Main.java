/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projekat.zoranvisic3061;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Zoran
 */
public class Main extends Application {

    /**
     * @param args the command line arguments
     */
    @Override
    public void start(Stage primaryStage) {
        Button btn1 = new Button();
        btn1.setText("Trazi automobile");
        Button btn2 = new Button();
        btn2.setText("Prodaj automobil");
        btn1.setOnAction((ActionEvent event) -> {
            try {
                runAnotherApp(Kupac.class);
                Stage stage = (Stage) btn1.getScene().getWindow();
                stage.close();
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    runAnotherApp(Prodavac.class);
                    Stage stage = (Stage) btn1.getScene().getWindow();
                    stage.close();
                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        final Label uvod = new Label("Dobrodosli!\nIzaberite opciju");
        uvod.setFont(new Font("Arial", 25));

        GridPane root = new GridPane();
        root.add(btn1, 0, 0);
        root.add(btn2, 1, 0);
        root.setHgap(10);

        final VBox vb = new VBox();
        vb.setSpacing(5);
        vb.setPadding(new Insets(50, 25, 50, 25));
        vb.getChildren().addAll(uvod, root);
        vb.setSpacing(5);
        Scene scene = new Scene(vb, 300, 250);

        primaryStage.setTitle("Prodaja automobila");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void runAnotherApp(Class<? extends Application> anotherAppClass) throws Exception {
        Application app2 = anotherAppClass.newInstance();
        Stage anotherStage = new Stage();
        app2.start(anotherStage);
    }
}
