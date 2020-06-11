package Service;

import Domeniu.Carte;
import Repository.RepoCarte;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceBibliotecar {
    private RepoCarte repoCarte;

    public ServiceBibliotecar(RepoCarte repoCarte){
        this.repoCarte = repoCarte;
    }

    public ArrayList<Carte> findAll(){
        return repoCarte.findAll();
    }

    public ArrayList<Carte> available(){
        return repoCarte.findAllAvailable();
    }

    public ArrayList<Carte> unavailable(){
        return repoCarte.findAllUnavailable();
    }

    public void returneaza(Carte carte){
        repoCarte.update("in stoc", carte);
    }


    public void save(Carte carte){
        repoCarte.save(carte);
    }

    public void delete(String cod){
        repoCarte.delete(cod);
    }

    public ArrayList<Carte> findOne(String cod){
        Carte carte = repoCarte.findOne(cod);
        HashMap<String, Carte> map = new HashMap<>();
        map.put(carte.getCod(), carte);
        return new ArrayList<>(map.values());
    }
}
