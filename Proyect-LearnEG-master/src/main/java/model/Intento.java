package model;

import java.time.LocalDateTime;

public class Intento {
    private int id;
    private int usuarioId;
    private int palabraId;
    private String traduccionUsuario;
    private boolean esCorrecta;
    private LocalDateTime fecha;

    // Constructor, getters y setters
    public Intento(int usuarioId, int palabraId, String traduccionUsuario, boolean esCorrecta) {
        this.usuarioId = usuarioId;
        this.palabraId = palabraId;
        this.traduccionUsuario = traduccionUsuario;
        this.esCorrecta = esCorrecta;
        this.fecha = LocalDateTime.now();
    }

    // Getters y setters
    public int getUsuarioId(){
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId){
        this.usuarioId = usuarioId;
    }

    public int getPalabraId(){
        return palabraId;
    }

    public void setPalabraId(int palabraId){
        this.palabraId = palabraId;
    }

    public String getTraduccionUsuario(){
        return traduccionUsuario;
    }

    public void setTraduccionUsuario(String traduccionUsuario){
        this.traduccionUsuario = traduccionUsuario;
    }

    public boolean isEsCorrecta(){
        return esCorrecta;
    }

    public void setEsCorrecta(boolean esCorrecta){
        this.esCorrecta = esCorrecta;
    }

    public LocalDateTime getFecha(){
        return fecha;
    }

    public void setFecha(LocalDateTime fecha){
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

