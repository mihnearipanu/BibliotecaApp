package Service;

import Domeniu.Abonat;
import Domeniu.Bibliotecar;
import Domeniu.User;
import Repository.RepoUser;

public class ServiceUser {
    private RepoUser repoUser;

    public ServiceUser(RepoUser repoUser){
        this.repoUser = repoUser;
    }

    public User getUser(String username, String password){
        return repoUser.findOne(username, password);
    }

    public Abonat getAbonat(String username, String password){
        return repoUser.findOneAbonat(username, password);
    }

    public Bibliotecar getBibliotecar(String username, String password){
        return repoUser.findOneBibliotecar(username, password);
    }
}
