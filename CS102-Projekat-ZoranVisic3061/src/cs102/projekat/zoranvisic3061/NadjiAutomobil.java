/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projekat.zoranvisic3061;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Zoran
 */
public class NadjiAutomobil {

    public NadjiAutomobil() {
    }

    public ObservableList<Automobil> vratiAutomobile(String marka, String model, int godiste, int kubikaza, String boja) throws IOException {
        ObservableList<Automobil> automobili = FXCollections.observableArrayList();
        DataOutputStream outputS = null;
        BufferedReader inputS = null;
        Socket server = null;
        try {
            server = new Socket("localhost", 7777);

            outputS = new DataOutputStream(server.getOutputStream());
            outputS.writeBytes(marka + '\n');
            outputS.writeBytes(model + '\n');
            outputS.writeBytes(String.valueOf(godiste) + '\n');
            outputS.writeBytes(String.valueOf(kubikaza) + '\n');
            outputS.writeBytes(boja + '\n');
            inputS = new BufferedReader(new InputStreamReader(server.getInputStream()));
            int brA = Integer.parseInt(inputS.readLine());
            int id, cena;
            for (int i = 0; i < brA; i++) {
                id = Integer.parseInt(inputS.readLine());
                marka = inputS.readLine();
                model = inputS.readLine();
                godiste = Integer.parseInt(inputS.readLine());
                kubikaza = Integer.parseInt(inputS.readLine());
                boja = inputS.readLine();
                cena = Integer.parseInt(inputS.readLine());
                Automobil a = new Automobil(id, marka, model, godiste, kubikaza, boja, cena);
                automobili.add(a);

            }
        } catch (IOException ex) {
            Logger.getLogger(Kupac.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            outputS.close();
            server.close();
            inputS.close();
        }
        return automobili;
    }
}
