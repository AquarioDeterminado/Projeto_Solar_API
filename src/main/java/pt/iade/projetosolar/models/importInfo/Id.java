package pt.iade.projetosolar.models.importInfo;

import java.io.Serializable;

public class Id implements Serializable {
    private int id;

    public Id(int id) {
        this.id = id;
    }

    public Id() {}

    public int id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}