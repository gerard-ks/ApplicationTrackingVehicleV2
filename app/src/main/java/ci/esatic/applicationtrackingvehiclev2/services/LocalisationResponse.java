package ci.esatic.applicationtrackingvehiclev2.services;

public class LocalisationResponse {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalisationResponse(String message) {
        this.message = message;
    }
}
