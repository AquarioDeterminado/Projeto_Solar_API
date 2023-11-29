package pt.iade.projetosolar.controllers;

public class LogInController {
    private String userName;
    private String password;

    public String getError() {
        return error;
    }

    private String error;

    LogInController (String userName, String password){
        this.userName = userName;
        this.password = password;
    }


    public boolean checkCredentials() {
        assert !userName.isEmpty() && password.isEmpty();
        // TODO: call to database;

        return false;
    }
}
