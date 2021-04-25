package Entities;

public class Aide {
    private int id;
    private int category_id;
    private String titre;
    private String description;
    private String adresse;

    public Aide(int id, int category_id, String titre, String description, String adresse) {
        this.id = id;
        this.category_id = category_id;
        this.titre = titre;
        this.description = description;
        this.adresse = adresse;
    }

    public Aide(int id, String titre, String description, String adresse, int category_id) {

    }

    public Aide() {

    }

    public Aide(String titre, String description, String adresse) {
        this.titre = titre;
        this.description = description;
        this.adresse = adresse;
    }

    public Integer getId() {
        return id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
