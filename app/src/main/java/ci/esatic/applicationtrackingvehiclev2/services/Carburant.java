package ci.esatic.applicationtrackingvehiclev2.services;

public class Carburant {
    private Integer id;
    private String numeroFacture;
    private Double nombreLitre;
    private Integer montantCarburant;
    private String created_at;
    private String updated_at;
    private Integer chauffeur_vehicule_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public Double getNombreLitre() {
        return nombreLitre;
    }

    public void setNombreLitre(Double nombreLitre) {
        this.nombreLitre = nombreLitre;
    }

    public Integer getMontantCarburant() {
        return montantCarburant;
    }

    public void setMontantCarburant(Integer montantCarburant) {
        this.montantCarburant = montantCarburant;
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

    public Integer getChauffeur_vehicule_id() {
        return chauffeur_vehicule_id;
    }

    public void setChauffeur_vehicule_id(Integer chauffeur_vehicule_id) {
        this.chauffeur_vehicule_id = chauffeur_vehicule_id;
    }

    public Carburant(Integer id, String numeroFacture, Double nombreLitre, Integer montantCarburant, String created_at, String updated_at, Integer chauffeur_vehicule_id) {
        this.id = id;
        this.numeroFacture = numeroFacture;
        this.nombreLitre = nombreLitre;
        this.montantCarburant = montantCarburant;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.chauffeur_vehicule_id = chauffeur_vehicule_id;
    }
}
