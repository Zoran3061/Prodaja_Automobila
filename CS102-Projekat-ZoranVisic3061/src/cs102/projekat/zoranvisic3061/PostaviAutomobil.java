/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projekat.zoranvisic3061;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zoran
 */
public class PostaviAutomobil {

    public PostaviAutomobil() {
    }

    public void postaviAuto(String marka, String model, int godiste, int kubikaza, String boja, int cena) throws IOException {
        DataOutputStream outputS = null;
        Socket server = null;
        try {
            server = new Socket("localhost", 7779);
            outputS = new DataOutputStream(server.getOutputStream());
            outputS.writeBytes(marka + '\n');
            outputS.writeBytes(model + '\n');
            outputS.writeBytes(String.valueOf(godiste) + '\n');
            outputS.writeBytes(String.valueOf(kubikaza) + '\n');
            outputS.writeBytes(boja + '\n');
            outputS.writeBytes(String.valueOf(cena) + '\n');
        } catch (IOException ex) {
            Logger.getLogger(KupiAutomobil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            outputS.close();
            server.close();
        }
    }
}

