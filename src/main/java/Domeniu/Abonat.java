package Domeniu;

public class Abonat extends User {
    private int cnp;
    private String nume;
    private String adresa;
    private int nrTel;

    public Abonat(String nume, String adresa, int cnp, int nrTel, String username, String password){
        super(username, password, "Abonat");
        this.nume = nume;
        this.adresa = adresa;
        this.cnp = cnp;
        this.nrTel = nrTel;
    }

    public int getCnp() {
        return cnp;
    }

    public void setCnp(int cnp) {
        this.cnp = cnp;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getNrTel() {
        return nrTel;
    }

    public void setNrTel(int nrTel) {
        this.nrTel = nrTel;
    }

}
