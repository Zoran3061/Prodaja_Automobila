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
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zoran
 */
public class Server {

    public static void main(String[] args) throws IOException {
        new Thread(() -> {

            ServerSocket kupiSocket = null;

            BufferedReader userInput = null;
            try {
                kupiSocket = new ServerSocket(7778);
                while (true) {
                    Socket connectionKupiSocket = kupiSocket.accept();
                    if (!connectionKupiSocket.isClosed()) {
                        userInput = new BufferedReader(
                                new InputStreamReader(connectionKupiSocket.getInputStream()));
                        int id = Integer.parseInt(userInput.readLine());
                        Baza.connect();
                        Baza.delete(id);
                        Baza.disconnect();
                        System.out.println("Auto kupljen");
                    }

                    connectionKupiSocket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {

                    kupiSocket.close();
                    userInput.close();
                } catch (IOException ex) {
                    Logger.getLogger(Kupac.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
        new Thread(() -> {
            ServerSocket kupacSocket = null;
            Socket connectionKupacSocket = null;
            DataOutputStream outputS = null;
            BufferedReader userInput = null;
            try {
                kupacSocket = new ServerSocket(7777);
                while (true) {

                    connectionKupacSocket = kupacSocket.accept();
                    userInput = new BufferedReader(
                            new InputStreamReader(connectionKupacSocket.getInputStream()));
                    System.out.println("Kupac konektovan");
                    String model, marka, boja;
                    int godiste, kubikaza;
                    if (!connectionKupacSocket.isClosed()) {
                        ArrayList<Automobil> a = new ArrayList<>();
                        model = userInput.readLine();
                        marka = userInput.readLine();
                        godiste = Integer.parseInt(userInput.readLine());
                        kubikaza = Integer.parseInt(userInput.readLine());
                        boja = userInput.readLine();

                        Baza.connect();
                        a = Baza.get(marka, model, godiste, kubikaza, boja);
                        Baza.disconnect();
                        outputS = new DataOutputStream(connectionKupacSocket.getOutputStream());
                        outputS.writeBytes(String.valueOf(a.size()) + '\n');
                        for (int i = 0; i < a.size(); i++) {
                            outputS.writeBytes(String.valueOf(a.get(i).getId()) + '\n');
                            outputS.writeBytes(a.get(i).getMarka() + '\n');
                            outputS.writeBytes(a.get(i).getModel() + '\n');
                            outputS.writeBytes(String.valueOf(a.get(i).getGodiste()) + '\n');
                            outputS.writeBytes(String.valueOf(a.get(i).getKubikaza()) + '\n');
                            outputS.writeBytes(a.get(i).getBoja() + '\n');
                            outputS.writeBytes(String.valueOf(a.get(i).getCena()) + '\n');

                        }
                    }

                }

            } catch (SocketException ex) {
                try {
                    Server.main(args);
                } catch (IOException ex1) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (IOException ex) {
            } finally {
                try {
                    kupacSocket.close();
                    connectionKupacSocket.close();
                    userInput.close();
                    outputS.close();
                } catch (IOException ex) {
                    Logger.getLogger(Kupac.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();

        new Thread(() -> {

            ServerSocket prodajSocket = null;

            BufferedReader userInput = null;
            try {
                prodajSocket = new ServerSocket(7779);
                while (true) {
                    Socket connectionProdajSocket = prodajSocket.accept();
                    if (!connectionProdajSocket.isClosed()) {
                        userInput = new BufferedReader(
                                new InputStreamReader(connectionProdajSocket.getInputStream()));
                        String marka = userInput.readLine();
                        String model = userInput.readLine();
                        int godiste = Integer.parseInt(userInput.readLine());
                        int kubikaza = Integer.parseInt(userInput.readLine());
                        String boja = userInput.readLine();
                        int cena = Integer.parseInt(userInput.readLine());
                        Baza.connect();
                        Baza.add(marka, model, godiste, kubikaza, boja, cena);
                        Baza.disconnect();
                        System.out.println("Oglas postavljen");
                    }

                    connectionProdajSocket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {

                    prodajSocket.close();
                    userInput.close();
                } catch (IOException ex) {
                    Logger.getLogger(Kupac.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }
}

