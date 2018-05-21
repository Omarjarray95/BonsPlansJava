/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techniques;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;

/**
 *
 * @author HP
 */
public class InputValidation {
     public static boolean validTextField(String test) {
        return test.equals("");
    }

    public static int validPwd(String mdp, String VMdp) {
        int i = 0;
        if ((!mdp.equals("")) && (!VMdp.equals("")) && (mdp.equals(VMdp))) {
            i = 1;
        }
        return i;
    }
    

    public static int validModifPwd(String mdp, String VMdp) {
        int i = 0;
        if ((!mdp.equals("")) && (!VMdp.equals("")) && (mdp.equals(VMdp))) {
            i = 1;
        } else if ((mdp.equals("")) && (VMdp.equals("")) && (mdp.equals(VMdp))) {
            i = 2;
        } else {
            i = 3;
        }
        return i;
    }

    public static boolean validEmail(String email) {
        boolean status = false;

        String emailPattern = "^[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(CharSequence.class.cast(email));
        if (matcher.matches()) {
            status = true;
        }
        return status;
    }

    public static boolean validUsername(String username) {
        boolean status = false;

        String usernamePattern = "^[a-zA-Z]+[A-Za-z0-9-]*$";
        Pattern pattern = Pattern.compile(usernamePattern);
        Matcher matcher = pattern.matcher(CharSequence.class.cast(username));
        if (matcher.matches()) {
            status = true;
        }
        return status;
    }
    
    public static int isAge(String ch) {
        int i = 0;
        if (ch.length() == 2) {
            try {
                i = Integer.parseInt(ch);
            } catch (NumberFormatException e) {
                return i;
            }
        }
        return i;
    }

    public static int isPhoneNumber(String ch) {
        int i = 0;
        if (ch.length() == 8) {
            try {
                i = Integer.parseInt(ch);
            } catch (NumberFormatException e) {
                return i;
            }
        }
        return i;
    }
     public static int isNumber(String ch) {
       int i=0;
            try {
                i = Integer.parseInt(ch);
            } catch (NumberFormatException e) {
                return i;
            }
        
        return i;
    }

    public Alert getAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert;
    }

}
