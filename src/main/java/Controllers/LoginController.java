package Controllers;

import Domeniu.Abonat;
import Domeniu.Bibliotecar;
import Domeniu.User;
import Repository.RepoCarte;
import Service.ServiceAbonat;
import Service.ServiceBibliotecar;
import Service.ServiceUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginController {
    ServiceUser serviceUser;
    Stage dialog;

    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button buttonLogin;

    public void setService(Stage dialog, ServiceUser serviceUser){
        this.dialog = dialog;
        this.serviceUser = serviceUser;
    }

    public void handleLogin(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.equals("") || password.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Eroare");
            alert.setContentText("Username si parola nu au voie sa fie goale!");
            alert.showAndWait();
        }
        else {
            User user = serviceUser.getUser(username, password);
            if (user == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Eroare");
                alert.setContentText("Userul nu a fost gasit!");
                alert.showAndWait();
            }
            else if (user.getRol().equals("abonat")) {
                Abonat abonat = serviceUser.getAbonat(username, password);
                launchAbonat(abonat);
            } else if (user.getRol().equals("bibliotecar")) {
                Bibliotecar biblio = serviceUser.getBibliotecar(username, password);
                launchBibliotecar(biblio);
            }
        }
    }

    public void launchAbonat(Abonat abonat){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("abonatView.fxml"));
            Parent root = loader.load();
            AbonatController abonatController = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            abonatController.setService(new ServiceAbonat(new RepoCarte()), stage);
            stage.setTitle("Bun venit, abonat " + abonat.getNume());
            stage.setScene(scene);
            stage.show();
            dialog.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void launchBibliotecar(Bibliotecar biblio){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("bibliotecarView.fxml"));
            Parent root = loader.load();
            BibliotecarController biblioController = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            biblioController.setService(new ServiceBibliotecar(new RepoCarte()), stage);
            stage.setTitle("Bun venit, bibliotecar " + biblio.getNume());
            stage.setScene(scene);
            stage.show();
            dialog.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
