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
public class NeodgovarajucaKubikazaException extends Exception {

    public NeodgovarajucaKubikazaException() {
    }

    public NeodgovarajucaKubikazaException(String message) {
        super(message);
    }
    @Override
    public String getMessage(){
        return "Unesite ispravnu kubikazu!";
    }
    public void show(NeodgovarajucaKubikazaException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Greska");
        alert.setContentText(ex.getMessage());
        alert.showAndWait();
    }
}