package pt.iade.projetosolar.models.exportInfo;

import pt.iade.projetosolar.models.dao.users.User;

public class LogInResponse {
    private int id;
    private String error;

    public LogInResponse(User user) {
        this.id = user.getId();
        this.error = null;
    }

    public LogInResponse(String error) {
        this.id = 0;
        this.error = error;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
