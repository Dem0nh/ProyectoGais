package ui;

import database.PalabraDAO;
import database.ProgresoDAO;
import ui.IntentoController;
import model.Intento;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Palabra;
import javafx.animation.PauseTransition;
import javafx.util.Duration;


public class LeccionController {

    //@FXML conecta con los atributos que tenemos en el FXML
    @FXML
    private Label lblPalabraIngles;

    @FXML
    private TextField txtTraduccion;

    @FXML
    private Button btnValidar;

    @FXML
    private Label lblResultado;

    //Declaramos nuestras variables

    private PalabraDAO palabraDAO; //De tipo PalabraDAO, para poder comunicarnos con la base de datos
    private Palabra palabraActual; //Es la palabra actual que se le está mostrando al usuario
    private IntentoController intentoController;


    @FXML
    public void initialize() { //Es el metodo que se inicia al abrir esta pantalla, llamamos a cargarNuevaPalabra para que nos muestre una palabra
        palabraDAO = new PalabraDAO();
        intentoController = new IntentoController();

        cargarNuevaPalabra();
    }

    @FXML
    private void cargarNuevaPalabra() {
        palabraActual = palabraDAO.obtenerPalabraAleatoria(); //Llama a la base de datos para obtener una palabra aleatoria

        if (palabraActual != null) {
            lblPalabraIngles.setText(palabraActual.getPalabraIngles()); //La muestra en el label
            txtTraduccion.clear(); //Limpia el texto
            lblResultado.setText("");
        } else {
            lblPalabraIngles.setText("No hay palabras disponibles");
            btnValidar.setDisable(true);
        }
    }

    @FXML
    private void validarRespuesta() {
        String respuestaUsuario = txtTraduccion.getText().trim().toLowerCase();
        String respuestaCorrecta = palabraActual.getTraduccionEspanol().trim().toLowerCase();

        boolean esCorrecta = respuestaUsuario.equals(respuestaCorrecta);

        if (esCorrecta) {
            lblResultado.setText("✅ ¡Correcto!");
            palabraActual.aumentarDificultad();
        } else {
            lblResultado.setText("❌ Incorrecto. Era: " + palabraActual.getTraduccionEspanol());
            palabraActual.reiniciarDificultad();
        }

        // Guarda el progreso (por ahora usuarioId = 1)
        Intento intento = new Intento(1, palabraActual.getId(), respuestaUsuario, esCorrecta);
        intentoController.registrarIntento(intento);

        // Actualiza dificultad en la base de datos
        palabraDAO.actualizarDificultad(palabraActual);

        // Espera 2 segundos antes de cargar una nueva palabra
        PauseTransition pausa = new PauseTransition(Duration.seconds(2));
        pausa.setOnFinished(event -> cargarNuevaPalabra());
        pausa.play();

    }


}
