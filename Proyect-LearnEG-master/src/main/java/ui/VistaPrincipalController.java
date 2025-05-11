package ui;

//Herramientas de JavaFX
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;

public class VistaPrincipalController {



    //Metodo que se ejecuta cuando se acciona el botón empezar lección
    @FXML
    private void empezarLeccion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/leccion.fxml")); //Se carga el archivo FXML con FXMLLOADER
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //Obtiene la ventana actual desde el botón que fue clickeado
        stage.setScene(new Scene(root)); //Cambia la escena de esa ventana y la reemplaza por la de leccion.fxml
    }


    @FXML
    private void handleVerProgreso(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/progreso.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    private void mostrarMensaje(String mensaje) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
