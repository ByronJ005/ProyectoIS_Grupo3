package modelo;

/**
 *
 * @author Asus
 */
public class Materia {
    private Integer id;
    private Integer id_curso;
    private String nombre;
    
    public Materia() {
    }

    public Materia(Integer id, Integer id_curso, String nombre) {
        this.id = id;
        this.id_curso = id_curso;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_curso() {
        return id_curso;
    }

    public void setId_curso(Integer id_curso) {
        this.id_curso = id_curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
