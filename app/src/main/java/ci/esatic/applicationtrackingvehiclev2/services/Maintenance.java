package ci.esatic.applicationtrackingvehiclev2.services;

public class Maintenance {

    private Integer id;
    private Integer numeroFacture;
    private String reparation;
    private Integer montantMaintenance;
    private String created_at;
    private String updated_at;
    private Integer chauffeur_vehicule_id;

    public Maintenance(Integer id, Integer numeroFacture, String reparation, Integer montantMaintenance, String created_at, String updated_at, Integer chauffeur_vehicule_id) {
        this.id = id;
        this.numeroFacture = numeroFacture;
        this.reparation = reparation;
        this.montantMaintenance = montantMaintenance;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.chauffeur_vehicule_id = chauffeur_vehicule_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroFacture() {
        return numeroFacture;
    }

    public void setNumeroFacture(Integer numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public String getReparation() {
        return reparation;
    }

    public void setReparation(String reparation) {
        this.reparation = reparation;
    }

    public Integer getMontantMaintenance() {
        return montantMaintenance;
    }

    public void setMontantMaintenance(Integer montantMaintenance) {
        this.montantMaintenance = montantMaintenance;
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
}
