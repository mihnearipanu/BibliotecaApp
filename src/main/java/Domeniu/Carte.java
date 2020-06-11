package Domeniu;

public class Carte {
    private String cod;
    private String titlu;
    private String autor;
    private String status;


    public Carte(String cod, String titlu, String autor, String status){
        this.cod = cod;
        this.titlu = titlu;
        this.autor = autor;
        this.status = status;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
