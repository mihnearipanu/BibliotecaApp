package Service;

import Domeniu.Carte;
import Repository.RepoCarte;

import java.util.ArrayList;

public class ServiceAbonat {
    private RepoCarte repoCarte;

    public ServiceAbonat(RepoCarte repoCarte){
        this.repoCarte = repoCarte;
    }

    public ArrayList<Carte> findAll(){
        return repoCarte.findAll();
    }

    public ArrayList<Carte> available(){
        return repoCarte.findAllAvailable();
    }

    public void inchiriere(Carte carte){
        repoCarte.update("indisponibil", carte);
    }
}
