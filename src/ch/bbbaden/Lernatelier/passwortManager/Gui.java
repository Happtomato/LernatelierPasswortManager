package ch.bbbaden.Lernatelier.passwortManager;

import javax.swing.*;

public class Gui {

    //Variables

    private int idDBL;
    private boolean newUserAdded = false;
    private boolean goBack = false;
    private boolean validationUser = false;
    private boolean repeatLoginProcedure = true;

    SQL_Statements statements = new SQL_Statements();

    public void gui() {
        //exit loop
        do {
            //Login window and add new user
            do {
                //variables
                String email;
                String password;

                //Welcome
                Object[] welcomeButtons = {"Anmelden", "Neuer Benutzer","Beenden"};
                int welcomeWindow = JOptionPane.showOptionDialog(null, "Willkommen beim Passwortmanager", "Willkommen", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, welcomeButtons, null);

                //get user_DBL_login

                //login
                if (welcomeWindow == JOptionPane.YES_OPTION) {
                    email = JOptionPane.showInputDialog("Bitte geben Sie ihre Email ein");
                    password = JOptionPane.showInputDialog("Bitte geben sie ihre Passwort ein");

                    validationUser = statements.validateUser(email, password);
                    //get user_id
                    idDBL = statements.getIdDBL();
                    newUserAdded = false;
                }
                //add new user
                else if (welcomeWindow == JOptionPane.NO_OPTION) {
                    email = JOptionPane.showInputDialog("Bitte geben Sie ihre Email ein");
                    password = JOptionPane.showInputDialog("Bitte geben sie ihre Passwort ein");
                    statements.addNewUser(email, password);
                    newUserAdded = true;
                }
                //exit program
                else if(welcomeWindow == JOptionPane.CANCEL_OPTION){
                    System.exit(0);
                }
                else{
                    System.exit(0);
                }
            } while (newUserAdded);

            //Show and add logins
            do {

                //check for validation
                if (validationUser) {
                    Object[] loginButtons = {"Logins hinzufügen", "Logins anzeigen", "Abmelden"};
                    int loginWindow = JOptionPane.showOptionDialog(null, "Was möchten Sie tun", "Logins", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, loginButtons, null);

                    //add new login
                    if (loginWindow == JOptionPane.YES_OPTION) {
                        //get user details
                        String newLoginWebsite = JOptionPane.showInputDialog("Geben sie die Website ein");
                        String newLoginLogin = JOptionPane.showInputDialog("Geben sie das Login ein");
                        String newLoginPassword = JOptionPane.showInputDialog("Geben sie das Passwort ein");

                        statements.addLogin(idDBL, newLoginWebsite, newLoginLogin, newLoginPassword);
                    }

                    //output of logins
                    else if (loginWindow == JOptionPane.NO_OPTION) {

                        Object[] outputLoginButtons = {"Alle Logins anzeigen", "Login suchen"};
                        int outputLoginWindow = JOptionPane.showOptionDialog(null, "was möchten Sie tun", "Ausgeben", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, outputLoginButtons, null);

                        //show all logins
                        if(outputLoginWindow == JOptionPane.YES_OPTION){
                            statements.getLogins(idDBL);
                            repeatLoginProcedure = true;
                        }
                        //search for Login
                        else if(outputLoginWindow == JOptionPane.NO_OPTION){
                            String websiteName = JOptionPane.showInputDialog("Bitte geben sie die Website ein.");
                            statements.searchLogin(idDBL, websiteName);
                            repeatLoginProcedure = true;
                        }
                        //Go Back
                        } else if (loginWindow == JOptionPane.CANCEL_OPTION) {
                        repeatLoginProcedure = false;
                        goBack = true;
                        }
                }
            } while (repeatLoginProcedure);
        }while(goBack);
        //exit program
        System.exit(0);
    }

}
