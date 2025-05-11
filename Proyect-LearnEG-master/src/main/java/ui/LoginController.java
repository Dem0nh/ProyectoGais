package ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import model.Login;  // Importa el modelo Login

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private Label lblMensaje;

    private final Login login = new Login();  // Creamos una instancia del modelo Login

    @FXML
    private void handleLogin() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = txtContrasena.getText().trim();

        // Verificar si los campos están vacíos
        if (usuario.isEmpty() || contrasena.isEmpty()) {
            lblMensaje.setText("Por favor complete todos los campos.");
            lblMensaje.setStyle("-fx-text-fill: red;");
            return;
        }

        // Intentar iniciar sesión usando el modelo Login
        boolean exito = login.inicioDeSesion(usuario, contrasena);
        if (exito) {
            lblMensaje.setText("Inicio de sesión exitoso.");
            lblMensaje.setStyle("-fx-text-fill: green;");

            // Redirigir a la vista principal
            redirigirAVistaPrincipal();
        } else {
            lblMensaje.setText("Usuario o contraseña incorrectos.");
            lblMensaje.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void handleRegister() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = txtContrasena.getText().trim();

        // Verificar si los campos están vacíos
        if (usuario.isEmpty() || contrasena.isEmpty()) {
            lblMensaje.setText("Por favor complete todos los campos.");
            lblMensaje.setStyle("-fx-text-fill: red;");
            return;
        }

        // Intentar registrar un nuevo usuario usando el modelo Login
        boolean registrado = login.registrar(usuario, contrasena);
        if (registrado) {
            lblMensaje.setText("Usuario registrado correctamente.");
            lblMensaje.setStyle("-fx-text-fill: green;");
        } else {
            lblMensaje.setText("El usuario ya existe.");
            lblMensaje.setStyle("-fx-text-fill: red;");
        }
    }

    // Método para redirigir a la vista principal
    private void redirigirAVistaPrincipal() {
        try {
            // Cargar la nueva escena de la vista principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Vista_Principal.fxml"));
            Parent root = loader.load();

            // Obtener el escenario actual y cambiar a la nueva escena
            Stage stage = (Stage) txtUsuario.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Vista Principal");
            stage.show();

            // Opcionalmente, cerrar la ventana de login si ya no la necesitas
            // stage.close();

        } catch (IOException e) {
            e.printStackTrace();
            lblMensaje.setText("Error al cargar la vista principal.");
            lblMensaje.setStyle("-fx-text-fill: red;");

        }
    }
}
