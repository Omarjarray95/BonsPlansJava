package entities;

import java.util.ArrayList;
import java.util.List;

public class Etablissement 
{
    private int id;
    private String nom;
    private String type;

    private int partenaire;

    public Etablissement(int id, String nom, String type, int partenaire, int responsable) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.partenaire = partenaire;
        this.responsable = responsable;
    }

    public int getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(int partenaire) {
        this.partenaire = partenaire;
    }

    private String adresse;
    private String description;
    private String horaire_ouverture;
    private String horaire_fermeture;
    private int numtel;
    private String url;
    private int budgetmoyen;
    private String image;
    private String type_resto;
    private String type_loisirs;
    private String type_shops;
    private String nbrStars;
    private String rating; 
    private int nbrRates;
    private int nombre; 

    private int responsable;

    public Etablissement(int id, String nom, String type, int responsable) 
    {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.responsable = responsable;
    }
    public Etablissement(){}

    public int getResponsable() {
        return responsable;

    }
    public Etablissement(int id,String nom, String type, String adresse, String description, String horaire_ouverture, String horaire_fermeture, int numtel, String url, int budgetmoyen, String image, String type_resto, String type_loisirs, String type_shops, String nbrStars,int resp) 
    {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.adresse = adresse;
        this.description = description;
        this.horaire_ouverture = horaire_ouverture;
        this.horaire_fermeture = horaire_fermeture;
        this.numtel = numtel;
        this.url = url;
        this.budgetmoyen = budgetmoyen;
        this.image = image;
        this.type_resto = type_resto;
        this.type_loisirs = type_loisirs;
        this.type_shops = type_shops;
        this.nbrStars = nbrStars;
        this.responsable=resp;
    }
    public Etablissement(int id,String nom, String type, String adresse, String description, String horaire_ouverture, String horaire_fermeture, int numtel, String url, int budgetmoyen, String image, String type_resto, String type_loisirs, String type_shops, String nbrStars,String rating,int nbrRates,int nombre, int resp) 
    {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.adresse = adresse;
        this.description = description;
        this.horaire_ouverture = horaire_ouverture;
        this.horaire_fermeture = horaire_fermeture;
        this.numtel = numtel;
        this.url = url;
        this.budgetmoyen = budgetmoyen;
        this.image = image;
        this.type_resto = type_resto;
        this.type_loisirs = type_loisirs;
        this.type_shops = type_shops;
        this.nbrStars = nbrStars;
        this.rating=rating;
        this.nbrRates=nbrRates;
        this.nombre=nombre;
        this.responsable=resp;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getNbrRates() {
        return nbrRates;
    }

    public void setNbrRates(int nbrRates) {
        this.nbrRates = nbrRates;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
    
    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getNom() 
    {
        return nom;
    }

    public void setNom(String nom) 
    {
        this.nom = nom;
    }

    public String getType() 
    {
        return type;
    }

    public void setType(String type) 
    {
        this.type = type;
    }

    public String getAdresse() 
    {
        return adresse;
    }

    public void setAdresse(String adresse) 
    {
        this.adresse = adresse;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getHoraire_ouverture() {
        return horaire_ouverture;
    }

    public void setHoraire_ouverture(String horaire_ouverture) {
        this.horaire_ouverture = horaire_ouverture;
    }

    public String getHoraire_fermeture() {
        return horaire_fermeture;
    }

    public void setHoraire_fermeture(String horaire_fermeture) {
        this.horaire_fermeture = horaire_fermeture;
    }

    public int getNumtel() 
    {
        return numtel;
    }

    public void setNumtel(int numtel) 
    {
        this.numtel = numtel;
    }

    public String getUrl() 
    {
        return url;
    }

    public void setUrl(String url) 
    {
        this.url = url;
    }

    public int getBudgetmoyen() 
    {
        return budgetmoyen;
    }

    public void setBudgetmoyen(int budgetmoyen) 
    {
        this.budgetmoyen = budgetmoyen;
    }

    public String getImage() 
    {
        return image;
    }

    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getType_resto() 
    {
        return type_resto;
    }

    public void setType_resto(String type_resto) 
    {
        this.type_resto = type_resto;
    }

    public String getType_loisirs() 
    {
        return type_loisirs;
    }

    public void setType_loisirs(String type_loisirs) 
    {
        this.type_loisirs = type_loisirs;
    }

    public String getType_shops() 
    {
        return type_shops;
    }

    public void setType_shops(String type_shops) 
    {
        this.type_shops = type_shops;
    }

    public String getNbrStars() 
    {
        return nbrStars;
    }

    public void setNbrStars(String nbrStars) 
    {
        this.nbrStars = nbrStars;
    }
    
    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 37 * hash + this.getId();
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
        {
            return true;
        }
        
        if (obj == null) 
        {
            return false;
        }
        
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        final Etablissement other = (Etablissement) obj;
        if (this.getId() != other.getId()) 
        {
            return false;
        }
        return true;
    }

    public void setResponsable(int responsable) 
    {
        this.responsable = responsable;
    }

    
    
}
