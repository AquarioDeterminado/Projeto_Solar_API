package pt.iade.projetosolar.models.importInfo;

public class ChangeUserString {
    int userId;
    String value;

    ChangeUserString(int userId, String value){
        this.userId = userId;
        this.value = value;
    }

    public int getUserId() {
        return userId;
    }

    public String getValue() {
        return value;
    }
}
