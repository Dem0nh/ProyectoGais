package ui;

import database.IntentoDAO;
import model.Intento;

import java.util.List;

public class IntentoController {

    private IntentoDAO intentoDAO;

    public IntentoController() {
        this.intentoDAO = new IntentoDAO();
    }

    public void registrarIntento(Intento intento) {
        intentoDAO.guardarIntento(intento);
    }

    public List<Intento> obtenerIntentosUsuario(int usuarioId) {
        return intentoDAO.obtenerIntentosPorUsuario(usuarioId);
    }

    public void reiniciarProgresoUsuario(int usuarioId) {
        intentoDAO.eliminarIntentosPorUsuario(usuarioId);
    }
}

