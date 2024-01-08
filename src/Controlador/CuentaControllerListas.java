
package controlador;

import controlador.TDALista.LinkedList;
import controlador.listas.DAO.DataAccesObject;
import controlador.util.Utilidades;
import java.lang.reflect.Field;
import modelo.Cuenta;
import modelo.Curso;

/**
 *
 * @author alexg
 */
public class CuentaControllerListas extends DataAccesObject<Cuenta>{
    
    private LinkedList<Cuenta> cuentas = new LinkedList<>();
    private Cuenta cuenta = new Cuenta();
    private Integer index = -1;
    
    public CuentaControllerListas() {
        super(Cuenta.class);
    }

    /**
     * @return the cuentas
     */
    public LinkedList<Cuenta> getCuentas() {
        if (cuentas.isEmpty()) 
            cuentas = listall();
            return cuentas;
    }

    /**
     * @param cuentas the cuentas to set
     */
    public void setCuentas(LinkedList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    /**
     * @return the cuenta
     */
    public Cuenta getCuenta() {
        if (cuenta == null) {
            cuenta = new Cuenta();
        }
        return cuenta;
    }

    /**
     * @param cuenta the materia to set
     */
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    public Boolean save() {
        this.cuenta.setId(generated_id());
        return save(cuenta);
    }
    
    public Boolean update(Integer index) {
        return update(cuenta, index);
    }
    
    public String generatedCode() {
        StringBuilder code = new StringBuilder();
        Integer lenght = listall().getSize() + 1;
        Integer pos = lenght.toString().length();
        for (int i = 0; i < (10 - pos); i++) {
            code.append("0");
        }
        code.append(lenght.toString());
        return code.toString();
    }
    
    public LinkedList<Cuenta> quickSort(Integer type, String field, LinkedList<Cuenta> lista) throws Exception {
        getCuenta();  
        Integer n = lista.getSize();    
        Cuenta[] c = lista.toArray();   
        Field faux = Utilidades.getField(Cuenta.class, field);    
        if (faux != null) {    
            quickSort(c, 0, n - 1, type, field);    
            lista = lista.toList(c);    
        } else {
            throw new Exception("No existe ese criterio de busqueda");      
        }
        return lista;      
    }

    //Metodo de Ordenamiento: QUICK SORT

    public void quickSort(Cuenta[] arreglo, int inicio, int fin, Integer type, String field) throws Exception {
        int i = inicio;
        int j = fin;
        Cuenta pivote = arreglo[(inicio + fin) / 2];
        do {
            while (arreglo[i].comparar(pivote, field, type)) {
                i++;
            }
            while (pivote.comparar(arreglo[j], field, type)) {
                j--;
            }
            if (i <= j) {
                Cuenta aux = arreglo[i];
                arreglo[i] = arreglo[j];
                arreglo[j] = aux;
                i++;
                j--;
            }
        } while (i <= j);

        if (inicio < j)
            quickSort(arreglo, inicio, j, type, field);
        if (i < fin)
            quickSort(arreglo, i, fin, type, field);
    }

    public LinkedList<Cuenta> buscarCorreo(LinkedList<Cuenta> lista, String text, String correo) throws Exception {
        LinkedList<Cuenta> lo = this.quickSort(0, text, lista);
        Cuenta[] c = lo.toArray();
        LinkedList<Cuenta> result = new LinkedList<>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (c[i].getCorreo().equals(correo)) {
                result.add(c[i]);
            }
        }
        return result;
    }

    public LinkedList<Cuenta> buscarClave(LinkedList<Cuenta> lista, String text, String clave) throws Exception {
        LinkedList<Cuenta> lo = this.quickSort(0, text, lista);
        Cuenta[] c = lo.toArray();
        LinkedList<Cuenta> result = new LinkedList<>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (c[i].getClave().toString().equals(clave)) {
                result.add(c[i]);
            }
        }
        return result;
    }    
    
    public int obtenerIdPorCorreo(LinkedList<Cuenta> lista, String correo) throws Exception {
        LinkedList<Cuenta> lo = this.quickSort(0, "correo", lista);
        Cuenta[] c = lo.toArray();
        for (int i = 0; i < lo.getSize(); i++) {
            if (c[i].getCorreo().equals(correo)) {
                return c[i].getId();
            }
        }
        return -1; // Devuelve -1 si no se encuentra ninguna cuenta con el correo ingresado
    }

    public int obtenerIdPorClave(LinkedList<Cuenta> lista, String clave) throws Exception {
        LinkedList<Cuenta> lo = this.quickSort(0, "clave", lista);
        Cuenta[] c = lo.toArray();
        for (int i = 0; i < lo.getSize(); i++) {
            if (c[i].getClave().toString().equals(clave)) {
                return c[i].getId();
            }
        }
        return -1; // Devuelve -1 si no se encuentra ninguna cuenta con la clave ingresada
    } 
    
//    public static Integer obtenerIdPorCorreoYClave(LinkedList<Cuenta> cuentas, String correo, String clave) {
//        for (Cuenta cuenta : cuentas) {
//            if (cuenta.getCorreo().equals(correo) && cuenta.getClave().equals(clave)) {
//                return cuenta.getId();
//            }
//        }
//        return -1; // Devuelve -1 si no se encuentra ninguna cuenta con el correo y la clave ingresados
//    }
//  
}
