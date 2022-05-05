package mx.edu.unistmo.repo.mobile.android.contactlist;

import java.io.Serializable;

public class Contact implements Serializable {

    private String name;
    private String telephone;
    private String email;

    public Contact() {

    }

    public Contact(String name, String telephone, String email) {
        setName(name);
        setTelephone(telephone);
        setEmail(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
