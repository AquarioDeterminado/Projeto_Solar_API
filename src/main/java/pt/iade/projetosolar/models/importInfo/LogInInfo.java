package pt.iade.projetosolar.models.importInfo;

import java.io.Serializable;

public class LogInInfo implements Serializable {
    String email;
    String password;

    public LogInInfo(String username, String password)
    {
        this.email = username;
        this.password = password;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }

}
