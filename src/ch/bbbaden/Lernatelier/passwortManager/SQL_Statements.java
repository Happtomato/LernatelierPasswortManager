package ch.bbbaden.Lernatelier.passwortManager;

import javax.swing.*;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQL_Statements {


    private int idDBL = 0;

    public void addNewUser(String email, String passwort){

        try
        {
            // create a mysql database connection
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/passwortmanager";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "D0minik2005");
            //Encrypt dblLoginPassword
            String value = "'"+email+"'" + "," + "'"+passwort+"'";

            String query = "INSERT INTO `passwortmanager`.`databaselogin` (`Email`, `Passwort`) VALUES ("+value+");";

            // create the java statement
            Statement st = conn.createStatement();
            // execute the query, and get a java resultset
            st.executeUpdate(query);

            conn.close();
            JOptionPane.showMessageDialog(null,"Benutzer erfolgreich hinzugefügt.");
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

    }
    public boolean validateUser(String email, String passwort){
        try
        {

            // create a mysql database connection
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/passwortmanager";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "D0minik2005");

            String query = "select email, passwort, ID_DataBaseLogin from databaselogin where email ="+"'"+email+"'"+";";




            // create the java statement
            Statement st = conn.createStatement();
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            String passwortOut ="";


            // iterate through the java resultset
            while (rs.next())
            {
                passwortOut = rs.getString("Passwort");
                idDBL = rs.getInt("ID_DatabaseLogin");
            }

            if(passwortOut.equals(passwort)){
                conn.close();
                JOptionPane.showMessageDialog(null,"Erfolgreich eingeloggt.");
                return true;
            }
            else{
                conn.close();
                JOptionPane.showMessageDialog(null,"Benutzer nicht gefunden.");
                return false;
            }
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }
    }
    public int getIdDBL() {
        return idDBL;
    }
    public void getLogins(int idDBL) {
        try {
            // create a mysql database connection
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/passwortmanager";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "D0minik2005");

            String query = "select * from logins where BenutzerID =" + "'" + idDBL + "'" + ";";


            // create the java statement
            Statement st = conn.createStatement();
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            // iterate through the java resultset

            String website = null;

            while (rs.next()) {
                website = rs.getString("Website");
                String login = rs.getString("Login");
                String passwort = rs.getString("Passwort");
                String output = website + " " + login + " " + passwort;
                JOptionPane.showMessageDialog(null,output);
            }


            if(website == null){
                JOptionPane.showMessageDialog(null,"Kein Login gefunden.");
            }

            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
    public void addLogin(int idDBL, String website, String login, String passwort){
        try {
            // create a mysql database connection
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/passwortmanager";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "D0minik2005");

            String query1 = "UPDATE `passwortmanager`.`logins` SET `BenutzerID` = '1' WHERE (`ID_Logins` = '1');";
            String query2 = "INSERT INTO `passwortmanager`.`logins` (`BenutzerID`, `Website`, `Login`, `Passwort`) VALUES('" + idDBL +"', '" + website+"', '" + login+"', '" + passwort +"');";

            // create the java statement
            Statement st = conn.createStatement();
            // execute the query, and get a java resultset
            st.executeUpdate(query1);
            st.executeUpdate(query2);
            conn.close();
            JOptionPane.showMessageDialog(null,"Login erfolgreich hinzugefügt.");
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
    public void searchLogin(int idDBL, String websiteName){
        try {
            // create a mysql database connection
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/passwortmanager";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "D0minik2005");

            String query = "select * from logins where BenutzerID  =" + "'" + idDBL + "'" + " and Website =" + "'" + websiteName + "' ;";


            // create the java statement
            Statement st = conn.createStatement();
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset

            String website = null;
            while (rs.next()) {
                website = rs.getString("Website");
                String login = rs.getString("Login");
                String passwort = rs.getString("Passwort");

                String output = website + " " + login + " " + passwort;
                JOptionPane.showMessageDialog(null,output);
            }

            conn.close();


            if(website == null){
                JOptionPane.showMessageDialog(null,"Kein Login gefunden.");
            }
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

    }
}
