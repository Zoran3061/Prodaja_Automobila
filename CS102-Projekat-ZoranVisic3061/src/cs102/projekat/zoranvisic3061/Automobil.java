/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projekat.zoranvisic3061;

/**
 *
 * @author Zoran
 */
public class Automobil {
    
private int id;
private String marka, model;
private int godiste, kubikaza;
private String boja;
private int cena;

    public Automobil() {
    }

    public Automobil(int id, String marka, String model, int godiste, int kubikaza, String boja, int cena) {
        this.id = id;
        this.marka = marka;
        this.model = model;
        this.godiste = godiste;
        this.kubikaza = kubikaza;
        this.boja = boja;
        this.cena = cena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getGodiste() {
        return godiste;
    }

    public void setGodiste(int godiste) {
        this.godiste = godiste;
    }

    public int getKubikaza() {
        return kubikaza;
    }

    public void setKubikaza(int kubikaza) {
        this.kubikaza = kubikaza;
    }

    public String getBoja() {
        return boja;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Automobil{" + "id=" + id + ", marka=" + marka + ", model=" + model + ", godiste=" + godiste + ", kubikaza=" + kubikaza + ", boja=" + boja + ", cena=" + cena + '}';
    }

}
