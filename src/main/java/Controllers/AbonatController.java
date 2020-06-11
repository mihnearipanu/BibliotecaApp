package Controllers;

import Domeniu.Carte;
import Service.ServiceAbonat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AbonatController {
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

    ServiceAbonat serviceAbonat;
    Stage dialog;

    @FXML
    private void initialize(){}

    public void setService(ServiceAbonat serviceAbonat, Stage dialog){
        this.dialog = dialog;
        this.serviceAbonat = serviceAbonat;
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
            ArrayList<Carte> carti = serviceAbonat.findAll();
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
            ArrayList<Carte> carti = serviceAbonat.available();
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

    public void handleRefresh(){
        initModel();
    }

    public void handleImprumuta(){
        Carte carte = tableView.getSelectionModel().getSelectedItem();
        if(carte != null){
            if(carte.getStatus().equals("in stoc")){
                serviceAbonat.inchiriere(carte);
                Alert mesaj = new Alert(Alert.AlertType.INFORMATION);
                mesaj.setTitle("Bravo!");
                mesaj.setContentText("Ati imprumutat cartea " + carte.getTitlu() +"!");
                mesaj.showAndWait();
                initModel();
            }else{
                Alert mesaj = new Alert(Alert.AlertType.ERROR);
                mesaj.setTitle("Eroare!");
                mesaj.setContentText("Cartea selectata nu este in stoc!");
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
