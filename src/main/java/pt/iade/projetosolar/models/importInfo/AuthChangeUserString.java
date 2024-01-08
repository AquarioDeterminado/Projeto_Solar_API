package pt.iade.projetosolar.models.importInfo;

import javax.swing.plaf.BorderUIResource;

public class AuthChangeUserString {
    private int userID;
    private String password;
    private String newValue;

    public AuthChangeUserString(int userID, String password, String newValue){
        this.userID = userID;
        this.password = password;
        this.newValue = newValue;
    }

    public int getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getNewValue() {
        return newValue;
    }
}
