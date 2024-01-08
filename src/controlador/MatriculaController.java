package controlador;
import controlador.TDALista.LinkedList;
import controlador.TDALista.exceptions.VacioException;
import controlador.listas.DAO.DataAccesObject;
import controlador.listas.DAO.TransferObject;
import java.lang.reflect.Field;
import modelo.Matricula;
import modelo.PeriodoAcademico;

/**
 *
 * @author asus
 */
public class MatriculaController extends DataAccesObject<Matricula>{
    private LinkedList<Matricula> matriculas = new LinkedList<>();
    private Matricula matricula = new Matricula();
    private Integer index = -1;
    
    public MatriculaController() {
        super(Matricula.class);
    }

    public LinkedList<Matricula> getMatriculas() {
        if(matriculas.isEmpty())
            matriculas = listall();
        return matriculas;
    }

    public void setMatriculas(LinkedList<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public Matricula getMatricula() {
        if(matricula == null)
            matricula = new Matricula();
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Integer getIndex() {
        return index;
    }
    
    public void setIndex(Integer index) {
        this.index = index;
    }

    public Boolean save() {
        Integer id = generated_id();
        this.matricula.setId(id);
        return save(matricula);
    }
    
    public Boolean update(Integer index) {
        return update(matricula, index);
    }
    
    public String generatedCode() {
        StringBuilder code = new StringBuilder();
        Integer lenght = listall().getSize() + 1;
        Integer pos = lenght.toString().length();
        for (int i = 0; i < (5 - pos); i++) {
            code.append("0");
        }
        code.append(lenght.toString());
        return code.toString();
    }
    public LinkedList<Matricula> quickSort (Matricula[] arreglo, int inicio , int fin, int orden, String field) throws VacioException {
        int i = inicio; // i siempre avanza en el arreglo hacia la derecha
        int j = fin; // j siempre avanza hacia la izquierda
        Matricula pivote = arreglo[(inicio + fin)/2] ;
        do{
            while(arreglo[i].comparar(pivote, field, orden))//si ya esta ordenado incrementa i
                i++;
            while(pivote.comparar(arreglo[j], field, orden))//si ya esta ordenado decrementa j
                j--;
            if(i <= j){// Hace el intercambio
                Matricula aux = arreglo[i];
                arreglo[i] = arreglo[j] ;
                arreglo[j] = aux ;
                i++;
                j--;
            }
            }while(i <= j);
            if(inicio < j)
                quickSort(arreglo,inicio,j, orden, field);// invocación recursiva
            if(i < fin)
                quickSort(arreglo, i , fin, orden, field);// invocacion recursiva
            return new LinkedList<Matricula>().toList(arreglo);
    }
    
    //Busqueda Lineal
    
    public LinkedList<Matricula> buscarVarios(String field, Object valor, Integer orden) throws VacioException{
        Matricula[] arreglo = getMatriculas().toArray();
        Matricula[] arrayOrdenado = this.quickSort(arreglo, 0, (arreglo.length - 1), 0, field).toArray();
        LinkedList<Matricula> result = new LinkedList<>();
        for(int i = 0; i < arrayOrdenado.length ; i++){
            int buscado = arrayOrdenado[i].esSimilar(field, valor);
            if(buscado == 0 && orden == 0)
                result.add(arrayOrdenado[i]);
            else if(buscado < 0 && orden == 1)
                result.add(arrayOrdenado[i]);
            else if(buscado > 0 && orden == 2)
                result.add(arrayOrdenado[i]);
        }
        return result;
    }

}
