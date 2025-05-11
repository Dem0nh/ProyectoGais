package model;

public class Usuario {
    private int id; //Id que representa al usuario único en la base de datos
    private String nombre; //Nombre del usuario

    //Constructor
    public Usuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    //Constructor para la base de datos
    public Usuario(String nombre) {
        this(-1, nombre); //usamos este constructor para que la base de datos le asigne la id al usuario automáticamente (auto increment)
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}

