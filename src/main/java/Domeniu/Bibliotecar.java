package Domeniu;

public class Bibliotecar extends User {
    private String nume;

    public Bibliotecar(String username, String password, String nume){
        super(username, password, "Bibliotecar");
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}
