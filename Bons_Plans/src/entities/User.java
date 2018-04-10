package entities;

import java.sql.Date;

public class User 
{
    public String DTYPE;
    public int id;
    public String Email;
    public Date birthDate;
    public String login;
    public String nom;
    public String password;
    public String sex;
    public int tel;
    
    

    public User(String DTYPE, int id, String Email, Date birthDate, String nom, String password, String prenom, String sex, int tel, String login) {
        this.DTYPE = DTYPE;
        this.id = id;
        this.Email = Email;
        this.birthDate = birthDate;
        this.nom = nom;
        this.password = password;
        this.sex=sex;
        this.tel=tel;
        this.login=login;
    }

    public String getDTYPE() {
        return DTYPE;
    }

    public void setDTYPE(String DTYPE) {
        this.DTYPE = DTYPE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
    public String valid;

    public String test;
    
}
