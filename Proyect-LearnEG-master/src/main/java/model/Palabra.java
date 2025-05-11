package model;

public class Palabra{
    //Atributos
    private int id; //Identificador único en la DB
    private String palabraIngles; //Nos referimos a la palabra en su traducción al inglés
    private String traduccionEspanol; //Nos referimos a la palabra en su traducción al español
    private String contexto; //La categoría o contexto donde se desarrolla la palabra
    private int dificultad; //Qué tanto se le difuculta al usuario esta palabra

    //Constructor
    public Palabra(int id, String palabraIngles, String traduccionEspanol,String contexto, int dificultad){
        this.id = id;
        this.palabraIngles = palabraIngles; //Obtenemos la palabra en inglés
        this.traduccionEspanol = traduccionEspanol; //Obtenemos la palabra en español
        this.contexto = contexto; // Obtenemos el contexto de la palabra
        this.dificultad = dificultad; //La dificultad la obtendremos con el tiempo
    }

    public Palabra(String palabraIngles, String traduccionEspanol,String contexto){
        this(-1, palabraIngles, traduccionEspanol, contexto, 1);
    }




    //Getters
    public String getPalabraIngles(){
        return palabraIngles;
    }
    public String getTraduccionEspanol(){
        return traduccionEspanol;
    }
    public String getContexto(){
        return contexto;
    }

    //Metodos

    public void aumentarDificultad() {
        if (dificultad < 3) dificultad++;
    }

    public void reiniciarDificultad() {
        dificultad = 1;
    }

    // Getters y setters
    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    //Getter y Setter del id
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
}
