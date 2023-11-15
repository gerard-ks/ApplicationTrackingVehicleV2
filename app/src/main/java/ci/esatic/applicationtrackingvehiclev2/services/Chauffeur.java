package ci.esatic.applicationtrackingvehiclev2.services;

public class Chauffeur {
    private Integer id;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String numeroPiece;
    private String telephone;
    private String adresse;
    private Integer salaire;
    private String nomUtilisateur;
    private String motPasse;
    private String created_at;
    private String updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNumeroPiece() {
        return numeroPiece;
    }

    public void setNumeroPiece(String numeroPiece) {
        this.numeroPiece = numeroPiece;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getSalaire() {
        return salaire;
    }

    public void setSalaire(Integer salaire) {
        this.salaire = salaire;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
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

    public Chauffeur(Integer id, String nom, String prenom, String dateNaissance, String numeroPiece, String telephone, String adresse, Integer salaire, String nomUtilisateur, String motPasse, String created_at, String updated_at) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.numeroPiece = numeroPiece;
        this.telephone = telephone;
        this.adresse = adresse;
        this.salaire = salaire;
        this.nomUtilisateur = nomUtilisateur;
        this.motPasse = motPasse;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
