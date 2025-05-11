package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Progreso {
    private StringProperty palabraIngles;
    private StringProperty traduccionEspanol;
    private StringProperty respuestaCorrecta;
    private StringProperty fecha;

    public Progreso(String palabraIngles, String traduccionEspanol, boolean respuestaCorrecta, String fecha) {
        this.palabraIngles = new SimpleStringProperty(palabraIngles);
        this.traduccionEspanol = new SimpleStringProperty(traduccionEspanol);
        this.respuestaCorrecta = new SimpleStringProperty(respuestaCorrecta ? "✅" : "❌");
        this.fecha = new SimpleStringProperty(fecha);
    }

    public StringProperty palabraInglesProperty() { return palabraIngles; }
    public StringProperty traduccionEspanolProperty() { return traduccionEspanol; }
    public StringProperty respuestaCorrectaProperty() { return respuestaCorrecta; }
    public StringProperty fechaProperty() { return fecha; }
}

