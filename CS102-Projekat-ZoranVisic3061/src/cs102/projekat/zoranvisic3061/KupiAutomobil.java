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
public class KupiAutomobil {

    public KupiAutomobil() {
    }

    public void kupiAuto(int id) throws IOException {
        DataOutputStream outputS = null;
        Socket server = null;
        try {
            server = new Socket("localhost", 7778);
            outputS = new DataOutputStream(server.getOutputStream());
            outputS.writeBytes(String.valueOf(id) + '\n');
        } catch (IOException ex) {
            Logger.getLogger(KupiAutomobil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            outputS.close();
            server.close();
        }
    }
}
