package TutorProgramacion;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

/**
 * Created by Carmelo Marín Abrego on 02/06/2016.
 */
public class Persona {
    private String nombre;
    private LocalDate nacimiento;
    private Genero genero;

    public Persona(String nombre, LocalDate nacimiento, Genero genero, Boolean activo) {
        this.genero = genero;
        this.nacimiento = nacimiento;
        this.nombre = nombre;
        this._activo = new SimpleBooleanProperty(activo);
    }

    enum  Genero {
        MASCULINO, FEMENINO
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public static ObservableList<Persona> getPersonList(){
        Persona p1 = new Persona("Juan",    LocalDate.now(), Genero.MASCULINO, true);
        Persona p2 = new Persona("Anabel",  LocalDate.now(), Genero.FEMENINO, false);
        Persona p3 = new Persona("Esteban", LocalDate.now(), Genero.MASCULINO, true);
        Persona p4 = new Persona("Lucia",   LocalDate.now(), Genero.FEMENINO, true);
        Persona p5 = new Persona("Lucas",   LocalDate.now(), Genero.MASCULINO, false);

        return FXCollections.observableArrayList(p1, p2, p3, p4, p5);
    }

    private BooleanProperty _activo;

    public final BooleanProperty activoProperty(){
        return this._activo;
    }

    public final Boolean getActivo() {
        return _activo.get();
    }

    public final void setActivo(Boolean activo) {
        this._activo.set(activo);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", nacimiento=" + nacimiento +
                ", genero=" + genero +
                ", activo=" + getActivo() +
                '}';
    }
}
