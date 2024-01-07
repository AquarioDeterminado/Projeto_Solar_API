package pt.iade.projetosolar.models.importInfo;

import java.io.Serializable;

public class UserId implements Serializable {
    private int id;

    public UserId(int id) {
        this.id = id;
    }

    public UserId() {}

    public int id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}