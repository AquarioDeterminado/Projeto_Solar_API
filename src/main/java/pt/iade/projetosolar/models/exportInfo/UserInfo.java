package pt.iade.projetosolar.models.exportInfo;

import pt.iade.projetosolar.models.dao.users.User;

import java.sql.Date;

public class UserInfo {
    int usr_id;
    String usr_email;
    String usr_name;

    public UserInfo(User user){
        usr_id = user.getId();
        usr_email = user.getEmail();
        usr_name = user.getUserName();
    }

    public int getUsr_id() {
        return usr_id;
    }

    public String getUsr_email() {
        return usr_email;
    }

    public String getUsr_name() {
        return usr_name;
    }
}
