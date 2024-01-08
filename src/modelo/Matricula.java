package modelo;
import controlador.TDALista.LinkedList;
import controlador.TDALista.exceptions.VacioException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Asus
 */
public class Matricula {
    private Integer id;
    private Integer id_estudiante;
    private Integer id_periodoAcademico;
    private String codigo;
    private Boolean gratuidad;

    public Matricula() {
    }

    public Matricula(Integer id, Integer id_estudiante, Integer id_periodoAcademico, String codigo, Boolean gratuidad) {
        this.id = id;
        this.id_estudiante = id_estudiante;
        this.id_periodoAcademico = id_periodoAcademico;
        this.codigo = codigo;
        this.gratuidad = gratuidad;
    }

    public Boolean comparar(Matricula m, String field, Integer type) throws VacioException{
        Estudiante[] estudiantes = new LinkedList<Estudiante>().toArray();
        Estudiante estActual = estudiantes[getId_estudiante() - 1];
        Estudiante estComp = estudiantes[m.getId_estudiante() - 1];
        switch (type) {
            case 0:
                if(field.equalsIgnoreCase("codigo"))
                    return getCodigo().compareToIgnoreCase(m.getCodigo()) < 0;
                else if(field.equalsIgnoreCase("Nombre Estudiante"))
                    return estActual.toString().compareToIgnoreCase(estComp.toString()) < 0;
            case 1:
                if(field.equalsIgnoreCase("codigo"))
                    return getCodigo().compareToIgnoreCase(m.getCodigo()) > 0;
                else if(field.equalsIgnoreCase("Nombre Estudiante"))
                    return estActual.toString().compareToIgnoreCase(estComp.toString()) > 0;
            default:
                throw new AssertionError();
        }
    }
    
    public Integer esSimilar(String field, Object valor) throws VacioException{
        if(field.equalsIgnoreCase("Nombre Estudiante")){
            String nomActual = new LinkedList<Estudiante>().get(getId_estudiante() - 1).toString().toLowerCase();
            if(nomActual.contains(valor.toString().toLowerCase()))
                return 0;
            else
                return 1;
        }else if(field.equalsIgnoreCase("Codigo")){
            if(getCodigo().toLowerCase().contains(valor.toString().toLowerCase()))
                return 0;
            else
                return 1;
        }
        return null;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_periodoAcademico() {
        return id_periodoAcademico;
    }

    public void setId_periodoAcademico(Integer id_periodoAcademico) {
        this.id_periodoAcademico = id_periodoAcademico;
    }

    public Boolean getGratuidad() {
        return gratuidad;
    }

    public void setGratuidad(Boolean gratuidad) {
        this.gratuidad = gratuidad;
    }

    

    public Integer getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(Integer id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }    
}
