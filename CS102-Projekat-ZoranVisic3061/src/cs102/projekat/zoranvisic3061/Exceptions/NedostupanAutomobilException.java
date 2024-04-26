/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projekat.zoranvisic3061.Exceptions;

import javafx.scene.control.Alert;

/**
 *
 * @author Zoran
 */
public class NedostupanAutomobilException extends Exception {

    public NedostupanAutomobilException() {
    }

    public NedostupanAutomobilException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Zao nam je, automobil vise nije dostupan";
    }

    public void show(NedostupanAutomobilException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Greska");
        alert.setContentText(ex.getMessage());
        alert.showAndWait();
    }
}

