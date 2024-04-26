/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projekat.zoranvisic3061;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Zoran
 */
public class ProveriDostupnost {

    public ProveriDostupnost() {
    }

    public boolean dostupan(int id) throws IOException {
        ObservableList<Automobil> automobili = FXCollections.observableArrayList();
        NadjiAutomobil instance = new NadjiAutomobil();
        int br = 0;
        automobili = instance.vratiAutomobile("", "", 0, 99999, "");
        for (int i = 0; i < automobili.size(); i++) {
            if (id == automobili.get(i).getId()) {
                br++;
            }
        }
        return br > 0;

    }

}
