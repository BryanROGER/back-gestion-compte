package fr.bryan_roger.gestionCompte.dto;

public class AuthentificationRequest {

    private String email;
    private String password;

    public AuthentificationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AuthentificationRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
