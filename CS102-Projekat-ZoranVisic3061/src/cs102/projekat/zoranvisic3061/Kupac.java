/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projekat.zoranvisic3061;

import cs102.projekat.zoranvisic3061.Exceptions.NedostupanAutomobilException;
import cs102.projekat.zoranvisic3061.Exceptions.NeodgovarajucaKubikazaException;
import cs102.projekat.zoranvisic3061.Exceptions.NeodgovarajuceGodisteException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Zoran
 */
public class Kupac extends Application {

    private final TableView<Automobil> table = new TableView<>();
    private ObservableList<Automobil> automobili = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Automobili na prodaju");
        primaryStage.setWidth(800);
        primaryStage.setHeight(500);

        final Label nazivT = new Label("Automobili");
        nazivT.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn<Automobil, String> modelTC = new TableColumn("Model");
        modelTC.setMinWidth(50);
        modelTC.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Automobil, String> markaTC = new TableColumn("Marka");
        markaTC.setMinWidth(50);
        markaTC.setCellValueFactory(new PropertyValueFactory<>("marka"));

        TableColumn<Automobil, Integer> godisteTC = new TableColumn("Godiste");
        godisteTC.setMinWidth(50);
        godisteTC.setCellValueFactory(new PropertyValueFactory<>("godiste"));

        TableColumn<Automobil, Integer> kubikazaTC = new TableColumn("Kubikaza");
        kubikazaTC.setMinWidth(50);
        kubikazaTC.setCellValueFactory(new PropertyValueFactory<>("kubikaza"));

        TableColumn<Automobil, String> bojaTC = new TableColumn("Boja");
        bojaTC.setMinWidth(50);
        bojaTC.setCellValueFactory(new PropertyValueFactory<>("boja"));

        TableColumn<Automobil, Integer> cenaTC = new TableColumn("Cena");
        cenaTC.setMinWidth(50);
        cenaTC.setCellValueFactory(new PropertyValueFactory<>("cena"));

        table.getColumns().addAll(markaTC, modelTC, godisteTC, kubikazaTC, bojaTC, cenaTC);

        final VBox vboxLeft = new VBox();
        vboxLeft.setSpacing(5);
        vboxLeft.setPadding(new Insets(10, 0, 0, 10));
        vboxLeft.getChildren().addAll(nazivT, table);

        TextField modelTX = new TextField();
        TextField markaTX = new TextField();
        TextField godisteTX = new TextField();
        TextField kubikazaTX = new TextField();
        TextField bojaTX = new TextField();
        modelTX.setPrefWidth(20);
        Button btnOsvezi = new Button("Osvezi");
        Button btnKupi = new Button("Kupi");

        btnOsvezi.setOnAction((ActionEvent event) -> {
            String marka, model, boja;
            int godiste, kubikaza, id, cena;
            try {
                marka = markaTX.getText();
                model = modelTX.getText();
                if (godisteTX.getText().length() != 0) {
                    godiste = Integer.valueOf(godisteTX.getText());
                    if (godiste < 1900 || godiste > 2017) {
                        throw new NeodgovarajuceGodisteException();
                    }
                } else {
                    godiste = 0;
                }
                if (kubikazaTX.getText().length() != 0) {
                    kubikaza = Integer.valueOf(kubikazaTX.getText());
                    if (kubikaza < 500 || kubikaza > 15000) {
                        throw new NeodgovarajucaKubikazaException();
                    }
                } else {
                    kubikaza = 99999;
                }
                boja = bojaTX.getText();

                NadjiAutomobil instance = new NadjiAutomobil();
                try {
                    automobili = instance.vratiAutomobile(marka, model, godiste, kubikaza, boja);
                } catch (IOException ex) {
                    Logger.getLogger(Kupac.class.getName()).log(Level.SEVERE, null, ex);
                }

                table.setItems(automobili);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greska");
                alert.setContentText("Molimo unesite brojeve za godiste, kubikazu i cenu!");
                alert.showAndWait();
            } catch (NeodgovarajuceGodisteException ex) {
                ex.show(ex);
            } catch (NeodgovarajucaKubikazaException ex) {
                ex.show(ex);
            }
        });

        btnKupi.setOnAction((ActionEvent event) -> {
            try {
                Automobil a = table.getSelectionModel().getSelectedItem();
                if (a == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Greska");
                    alert.setContentText("Molimo izaberite automobil koji zelite da kupite!");
                    alert.showAndWait();
                } else {
                    ProveriDostupnost instanceD = new ProveriDostupnost();
                    if (instanceD.dostupan(a.getId())) {
                        KupiAutomobil instance = new KupiAutomobil();
                        instance.kupiAuto(a.getId());
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Uspesno");
                        alert.setHeaderText(null);
                        alert.setContentText("Kupili ste automobil!");
                        alert.showAndWait();
                    } else {
                        throw new NedostupanAutomobilException();
                    }
                }
            } catch (IOException | NullPointerException ex) {
            } catch (NedostupanAutomobilException ex) {
                ex.show(ex);
            }
        });

        GridPane gpane = new GridPane();
        gpane.add(new Label("Marka"), 0, 0);
        gpane.add(markaTX, 1, 0);
        gpane.add(new Label("Model"), 0, 1);
        gpane.add(modelTX, 1, 1);
        gpane.add(new Label("Min godiste"), 0, 2);
        gpane.add(godisteTX, 1, 2);
        gpane.add(new Label("Max kubikaza"), 0, 3);
        gpane.add(kubikazaTX, 1, 3);
        gpane.add(new Label("Boja"), 0, 4);
        gpane.add(bojaTX, 1, 4);
        gpane.add(btnOsvezi, 0, 5);
        gpane.add(btnKupi, 1, 5);
        gpane.setVgap(40);
        gpane.setHgap(15);
        gpane.setPadding(new Insets(50, 10, 10, 10));

        final VBox vboxRight = new VBox();
        vboxRight.getChildren().addAll(gpane);

        final HBox hbox = new HBox();
        hbox.setSpacing(5);
        hbox.setPadding(new Insets(10, 0, 0, 10));
        hbox.getChildren().addAll(vboxLeft, vboxRight);

        ((Group) scene.getRoot()).getChildren().addAll(hbox);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
