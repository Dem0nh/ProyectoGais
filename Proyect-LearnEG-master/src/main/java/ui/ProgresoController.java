package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import model.Progreso;
import database.ProgresoDAO;

import java.io.IOException;
import java.util.List;

public class ProgresoController {

    @FXML private TableView<Progreso> tablaProgreso;
    @FXML private TableColumn<Progreso, String> colPalabra;
    @FXML private TableColumn<Progreso, String> colTraduccion;
    @FXML private TableColumn<Progreso, String> colCorrecta;
    @FXML private TableColumn<Progreso, String> colFecha;

    @FXML
    public void initialize() {
        colPalabra.setCellValueFactory(data -> data.getValue().palabraInglesProperty());
        colTraduccion.setCellValueFactory(data -> data.getValue().traduccionEspanolProperty());
        colCorrecta.setCellValueFactory(data -> data.getValue().respuestaCorrectaProperty());
        colFecha.setCellValueFactory(data -> data.getValue().fechaProperty());

        List<Progreso> datos = ProgresoDAO.obtenerProgresoConDetalles();
        tablaProgreso.setItems(FXCollections.observableArrayList(datos));
    }

    @FXML
    private void volverAlMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista_principal.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}

