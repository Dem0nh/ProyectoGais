package model;

public class Login {
    private final String[] usuarios = new String[100];
    private final String[] contraseñas = new String[100];
    private int cantidadUsuarios = 0;

    // Metodo para registrar un nuevo usuario
    public boolean registrar(String usuario, String contraseña) {
        // Verificar si el usuario ya existe
        for (int i = 0; i < cantidadUsuarios; i++) {
            if (usuarios[i].equals(usuario)) {
                return false; // Usuario ya existe
            }
        }
        // Agregar usuario nuevo
        usuarios[cantidadUsuarios] = usuario;
        contraseñas[cantidadUsuarios] = contraseña;
        cantidadUsuarios++;
        return true;
    }

    // Metodo para iniciar sesión
    public boolean inicioDeSesion(String usuario, String contraseña) {
        for (int i = 0; i < cantidadUsuarios; i++) {
            if (usuarios[i].equals(usuario) && contraseñas[i].equals(contraseña)) {
                return true; // Usuario y contraseña coinciden
            }
        }
        return false; // No se encontró una coincidencia

        }

}
