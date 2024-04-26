/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projekat.zoranvisic3061;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zoran
 */
public class Baza {

private static Connection con = null;
private static String url = "jdbc:mysql://localhost/phpmyadmin";
private static String user = "root";
private static String pass = "";
private static String query = "";

    public static void connect() {
        try {
            con = (Connection) DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(Baza.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void disconnect() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Baza.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ArrayList<Automobil> get(String modelS, String markaS, int godisteS, int kubikazaS, String bojaS) {
        ArrayList<Automobil> automobili = new ArrayList<>();

        try {
            PreparedStatement st = (PreparedStatement) con.prepareStatement(query);
            query = "SELECT * FROM `automobili` WHERE `marka` LIKE \"%" + markaS + "\" "
                    + "AND `model` LIKE \"%" + modelS + "\" AND `godiste`>=" + godisteS + ""
                    + " AND `kubikaza`<=" + kubikazaS + " AND `boja` LIKE \"%" + bojaS + "\"";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String marka = rs.getString("marka");
                String model = rs.getString("model");
                int godiste = rs.getInt("godiste");
                int kubikaza = rs.getInt("kubikaza");
                String boja = rs.getString("boja");
                int cena = rs.getInt("cena");
                Automobil a = new Automobil(id, marka, model, godiste, kubikaza, boja, cena);
                automobili.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Baza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return automobili;

    }

    public static void delete(int id) {

        try {
            query = "DELETE FROM automobili WHERE `id`=" + id + "";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Baza.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void add(String marka, String model, int godiste, int kubikaza, String boja, int cena) {

        try {
            query = "INSERT INTO `automobili` (`id`, `marka`, `model`, `godiste`, `kubikaza`, `boja`, `cena`) "
                    + "VALUES (NULL, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ps.setString(1, marka);
            ps.setString(2, model);
            ps.setInt(3, godiste);
            ps.setInt(4, kubikaza);
            ps.setString(5, boja);
            ps.setInt(6, cena);
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Baza.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
