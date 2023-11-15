package ci.esatic.applicationtrackingvehiclev2.services;

public class Localisation {

    private Integer id;
    private String longitude;
    private String latitude;
    private String created_at;
    private String updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Localisation(Integer id, String longitude, String latitude, String created_at, String updated_at) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
