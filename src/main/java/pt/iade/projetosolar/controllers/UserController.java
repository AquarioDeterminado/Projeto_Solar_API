package pt.iade.projetosolar.controllers;

import pt.iade.projetosolar.models.dao.User;

public class UserController {

    public static User getUserFromDBO (String userName, String passWord){
        return new User(userName, passWord);
    }
}
