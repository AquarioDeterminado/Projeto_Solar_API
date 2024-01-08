package pt.iade.projetosolar.models.exportInfo;

public class RSVPInfo {

    String message;
    boolean success;
    int id;

    public RSVPInfo(String message, boolean success, int id) {
        this.message = message;
        this.success = success;
        this.id = id;
    }

    public RSVPInfo(int eventId) {
        this.id = eventId;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getId() {
        return id;
    }

    public void setError(String message) {
        this.message = message;
        this.success = false;
    }

    public void setSuccess(boolean success) {
        this.success = success;
        this.message = "Success";
    }

}
