package ci.esatic.applicationtrackingvehiclev2.services;

public class MaintenanceResponse {
    private String message;

    public MaintenanceResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
