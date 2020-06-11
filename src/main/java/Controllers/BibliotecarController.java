package Controllers;

import Domeniu.Carte;
import Service.ServiceAbonat;
import Service.ServiceBibliotecar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BibliotecarController {
    ObservableList<Carte> model = FXCollections.observableArrayList();

    @FXML
    TableView<Carte> tableView;
    @FXML
    private TableColumn<Carte, String> tableColumnCod;
    @FXML
    private TableColumn<Carte, String> tableColumnTitlu;
    @FXML
    private TableColumn<Carte, String> tableColumnAutor;
    @FXML
    private TableColumn<Carte, String> tableColumnDisponibilitate;
    @FXML
    private TextField textFieldCod;
    @FXML
    private TextField textFieldTitlu;
    @FXML
    private TextField textFieldAutor;

    ServiceBibliotecar serviceBibliotecar;
    Stage dialog;

    @FXML
    private void initialize(){}

    public void setService(ServiceBibliotecar serviceBibliotecar, Stage dialog){
        this.dialog = dialog;
        this.serviceBibliotecar = serviceBibliotecar;
        initModel();
        setTable();
    }

    @FXML
    private void setTable(){
        tableColumnCod.setCellValueFactory(new PropertyValueFactory<Carte, String>("cod"));
        tableColumnTitlu.setCellValueFactory(new PropertyValueFactory<Carte, String>("titlu"));
        tableColumnAutor.setCellValueFactory(new PropertyValueFactory<Carte, String>("autor"));
        tableColumnDisponibilitate.setCellValueFactory(new PropertyValueFactory<Carte, String>("status"));
    }

    private void initModel(){
        try{
            ArrayList<Carte> carti = serviceBibliotecar.findAll();
            model.setAll(carti);
            tableView.setItems(model);
            tableView.refresh();
            tableView.getSelectionModel().select(null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initAvailable(){
        try{
            ArrayList<Carte> carti = serviceBibliotecar.available();
            model.setAll(carti);
            tableView.setItems(model);
            tableView.refresh();
            tableView.getSelectionModel().select(null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initUnavailable(){
        try{
            ArrayList<Carte> carti = serviceBibliotecar.unavailable();
            model.setAll(carti);
            tableView.setItems(model);
            tableView.refresh();
            tableView.getSelectionModel().select(null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initModel(ArrayList<Carte> carti){
        try{
            model.setAll(carti);
            tableView.setItems(model);
            tableView.refresh();
            tableView.getSelectionModel().select(null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleAvailable(){
        initAvailable();
    }

    public void handleUnavailable(){
        initUnavailable();
    }

    public void handleRefresh(){
        initModel();
    }

    public void handleAdd(){
        String cod = textFieldCod.getText();
        String titlu = textFieldTitlu.getText();
        String autor = textFieldAutor.getText();
        if(cod.equals("") || titlu.equals("") || autor.equals("")){
            Alert mesaj = new Alert(Alert.AlertType.ERROR);
            mesaj.setTitle("Eroare!");
            mesaj.setContentText("Campurile nu pot fi goale!");
            mesaj.showAndWait();
        }else{
            Carte carte = new Carte(cod, titlu, autor, "in stoc");
            serviceBibliotecar.save(carte);
            initModel();
        }
    }

    public void handleDelete(){
        Carte carte = tableView.getSelectionModel().getSelectedItem();
        if(carte != null){
            serviceBibliotecar.delete(carte.getCod());
            initModel();
        }
        else{
            Alert mesaj = new Alert(Alert.AlertType.ERROR);
            mesaj.setTitle("Eroare!");
            mesaj.setContentText("Va rugam selectati o carte!");
            mesaj.showAndWait();
        }
    }

    public void handleFind(){
        String cod = textFieldCod.getText();
        if(cod.equals("")){
            Alert mesaj = new Alert(Alert.AlertType.ERROR);
            mesaj.setTitle("Eroare!");
            mesaj.setContentText("Va rugam introduceti codul unei carti!");
            mesaj.showAndWait();
        }else{
            initModel(serviceBibliotecar.findOne(cod));
        }
    }

    public void handleRestituie(){
        Carte carte = tableView.getSelectionModel().getSelectedItem();
        if(carte != null){
            if(carte.getStatus().equals("indisponibil")) {
                serviceBibliotecar.returneaza(carte);
                Alert mesaj = new Alert(Alert.AlertType.INFORMATION);
                mesaj.setTitle("Bravo!");
                mesaj.setContentText("Ati restituit cartea " + carte.getTitlu() +"!");
                mesaj.showAndWait();
                initModel();
            }else{
                Alert mesaj = new Alert(Alert.AlertType.ERROR);
                mesaj.setTitle("Eroare!");
                mesaj.setContentText("Cartea selectata este deja in stoc!");
                mesaj.showAndWait();
            }
        }
        else{
            Alert mesaj = new Alert(Alert.AlertType.ERROR);
            mesaj.setTitle("Eroare!");
            mesaj.setContentText("Va rugam selectati o carte!");
            mesaj.showAndWait();
        }
    }
}
