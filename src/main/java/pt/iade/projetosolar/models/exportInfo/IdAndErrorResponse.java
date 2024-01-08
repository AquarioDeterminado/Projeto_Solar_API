package pt.iade.projetosolar.models.exportInfo;

import pt.iade.projetosolar.models.dao.users.User;

public class IdAndErrorResponse {
    private int id;
    private String error;

    public IdAndErrorResponse(int id) {
        this.id = id;
        this.error = "";
    }

    public IdAndErrorResponse(String error) {
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
