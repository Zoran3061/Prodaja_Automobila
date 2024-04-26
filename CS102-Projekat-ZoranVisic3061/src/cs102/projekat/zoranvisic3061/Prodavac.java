/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projekat.zoranvisic3061;

import cs102.projekat.zoranvisic3061.Exceptions.NeodgovarajucaCenaException;
import cs102.projekat.zoranvisic3061.Exceptions.NeodgovarajucaKubikazaException;
import cs102.projekat.zoranvisic3061.Exceptions.NeodgovarajuceGodisteException;
import cs102.projekat.zoranvisic3061.Exceptions.PraznoPoljeException;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


/**
 *
 * @author Zoran
 */
public class Prodavac extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Prodajte automobil");
        primaryStage.setWidth(275);
        primaryStage.setHeight(350);

        final Label nazivT = new Label("Unesite informacije o vozilu");
        nazivT.setFont(new Font("Arial", 20));
        TextField modelTX = new TextField();
        TextField markaTX = new TextField();
        TextField godisteTX = new TextField();
        TextField kubikazaTX = new TextField();
        TextField bojaTX = new TextField();
        TextField cenaTX = new TextField();

        Button btn = new Button("Postavi oglas");

        GridPane grid = new GridPane();
        grid.add(new Label("Marka"), 0, 0);
        grid.add(markaTX, 1, 0);
        grid.add(new Label("Model"), 0, 1);
        grid.add(modelTX, 1, 1);
        grid.add(new Label("Godiste"), 0, 2);
        grid.add(godisteTX, 1, 2);
        grid.add(new Label("Kubikaza"), 0, 3);
        grid.add(kubikazaTX, 1, 3);
        grid.add(new Label("Boja"), 0, 4);
        grid.add(bojaTX, 1, 4);
        grid.add(new Label("Cena"), 0, 5);
        grid.add(cenaTX, 1, 5);
        grid.add(btn, 1, 6);
        grid.setHgap(30);
        grid.setVgap(15);

        final VBox vb = new VBox();
        vb.setSpacing(5);
        vb.setPadding(new Insets(10, 0, 0, 10));
        vb.getChildren().addAll(nazivT, grid);

        modelTX.setPrefWidth(20);

        btn.setOnAction((ActionEvent event) -> {
            try {
                String marka, model, boja;
                int godiste, kubikaza, cena;
                if (markaTX.getText().trim().isEmpty()
                        || modelTX.getText().trim().isEmpty()
                        || godisteTX.getText().trim().isEmpty()
                        || kubikazaTX.getText().trim().isEmpty()
                        || bojaTX.getText().trim().isEmpty()
                        || cenaTX.getText().trim().isEmpty()) {
                    throw new PraznoPoljeException();
                }
                marka = markaTX.getText();
                model = modelTX.getText();
                godiste = Integer.parseInt(godisteTX.getText());
                kubikaza = Integer.parseInt(kubikazaTX.getText());
                boja = bojaTX.getText();
                cena = Integer.parseInt(cenaTX.getText());
                if (godiste < 1900 || godiste > 2017) {
                    throw new NeodgovarajuceGodisteException();
                }
                if (kubikaza < 500 || kubikaza > 15000) {
                    throw new NeodgovarajucaKubikazaException();
                }
                if (cena < 0) {
                    throw new NeodgovarajucaCenaException();
                }
                PostaviAutomobil instance = new PostaviAutomobil();
                instance.postaviAuto(marka, model, godiste, kubikaza, boja, cena);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Obavestenje");
                alert.setHeaderText("Oglas uspesno postavljen!");
                alert.setContentText("Da li zelite da se vratite na pocetnu stranu?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    runAnotherApp(Main.class);
                    Stage stage = (Stage) btn.getScene().getWindow();
                    stage.close();
                }
            } catch (PraznoPoljeException ex) {
                ex.show(ex);

            } catch (NeodgovarajucaKubikazaException ex) {
                ex.show(ex);

            } catch (NeodgovarajuceGodisteException ex) {
                ex.show(ex);

            } catch (NeodgovarajucaCenaException ex) {
                ex.show(ex);

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greska");
                alert.setContentText("Molimo unesite brojeve za godiste, kubikazu i cenu!");
                alert.showAndWait();
            } catch (IOException ex) {
                Logger.getLogger(Prodavac.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Prodavac.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        ((Group) scene.getRoot()).getChildren().addAll(vb);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
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

