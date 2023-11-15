package ci.esatic.applicationtrackingvehiclev2.services;

public class ChauffeurResponse {

    private Chauffeur chauffeur;
    private String token;
    private String immatriculation;
    private Integer idConduite;
    private Integer idVehicule;

    public ChauffeurResponse(Chauffeur chauffeur, String token, String immatriculation, Integer idConduite, Integer idVehicule) {
        this.chauffeur = chauffeur;
        this.token = token;
        this.immatriculation = immatriculation;
        this.idConduite = idConduite;
        this.idVehicule = idVehicule;
    }

    public Chauffeur getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public Integer getIdConduite() {
        return idConduite;
    }

    public void setIdConduite(Integer idConduite) {
        this.idConduite = idConduite;
    }

    public Integer getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(Integer idVehicule) {
        this.idVehicule = idVehicule;
    }
}
