/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import controlador.TDALista.LinkedList;
import controlador.TDALista.exceptions.VacioException;
import controlador.listas.DAO.DataAccesObject;
import modelo.Cursa;

/**
 *
 * @author apolo
 */
public class CursaController extends DataAccesObject<Cursa>{
    private Cursa cursa = new Cursa();
    private LinkedList<Cursa> cursas = new LinkedList<>();
    
    public CursaController() {
        super(Cursa.class);
    }
    
    
    public Boolean guardar() {
        cursa.setId(generated_id());
        return save(cursa);
    }

    public Boolean update(int fila) {
        return update(cursa, fila);
    }

//    public String generatedCode() {
//        StringBuilder code = new StringBuilder();
//        Integer length = listall().getSize() + 1;
//        Integer pos = length.toString().length();
//        for (int i = 0; i < (10 - pos); i++) {
//            code.append("0");
//        }
//        code.append(length.toString());
//        return code.toString();
//    }


    public LinkedList<Cursa> mergeSort(LinkedList<Cursa> lista, Integer type, String field) {
        Cursa[] m = lista.toArray();
        mergeSort(m, 0, m.length - 1, field, type);
        return lista = lista.toList(m);
    }

    private void mergeSort(Cursa[] m, Integer ini, Integer fin, String field, Integer type) {
        if (ini < fin) {
            int medio = (ini + fin) / 2;
            mergeSort(m, ini, medio, field, type);
            mergeSort(m, medio + 1, fin, field, type);
            merge(m, ini, medio, fin, field, type);
        }
    }

    private void merge(Cursa[] m, Integer ini, Integer medio, Integer fin, String field, Integer type) {
        Integer izq = ini;
        Integer der = medio + 1;
        Integer k = 0;
        Integer n = fin - ini + 1;
        Cursa[] result = new Cursa[n];
        while (izq <= medio && der <= fin) {
            if (m[izq].comparar(m[der], field, type)) {
                result[k] = m[izq];
                izq++;
            } else {
                result[k] = m[der];
                der++;
            }
            k++;
        }
        while (izq <= medio) {
            result[k] = m[izq];
            izq++;
            k++;
        }
        while (der <= fin) {
            result[k] = m[der];
            der++;
            k++;
        }
        for (k = 0; k < n; k++) {
            m[ini + k] = result[k];
        }
    }

    public LinkedList<Cursa> quickSort(LinkedList<Cursa> lista, Integer type, String field) {
        Cursa[] m = lista.toArray();
        Integer fin = m.length - 1;
        quickSort(m, type, field, 0, fin);
        //setVentas(lista.toList(m));
        return lista = lista.toList(m);
    }

    private void quickSort(Cursa[] m, Integer type, String field, Integer inicio, Integer fin) {
        
        if (inicio >= fin) return;
        
        Cursa pivote = m[inicio];
        Integer elemIzq = inicio + 1;
        Integer elemDer = fin;
        while (elemIzq <= elemDer) {
            while (elemIzq <= fin && m[elemIzq].comparar(pivote, field, type)) {
                elemIzq++;
            }
            while (elemDer > inicio && !m[elemDer].comparar(pivote, field, type)) {
                elemDer--;
            }
            if (elemIzq < elemDer) {
                Cursa temp = m[elemIzq];
                m[elemIzq] = m[elemDer];
                m[elemDer] = temp;
            }
        }
        if (elemDer > inicio) {
            Cursa temp = m[inicio];
            m[inicio] = m[elemDer];
            m[elemDer] = temp;
        }
        quickSort(m, type, field, inicio, elemDer - 1);
        quickSort(m, type, field, elemDer + 1, fin);
    }

    public LinkedList<Cursa> buscar(LinkedList<Cursa> lista, String text) throws VacioException, Exception {
        LinkedList<Cursa> lo = this.quickSort(lista, 0, "id");
        Cursa[] m = lo.toArray();
        LinkedList<Cursa> result = new LinkedList<>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (m[i].getId().toString().toLowerCase().contains(text.toLowerCase())) {
                result.add(m[i]);
            }
        }
        return result;
    }
    
    public LinkedList<Cursa> buscarBinario(){
        return null;
    }
    
    /**
     * @return the cursa
     */
    public Cursa getCursa() {
        if(cursa == null) cursa = new Cursa();
        return cursa;
    }

    /**
     * @param cursa the cursa to set
     */
    public void setCursa(Cursa cursa) {
        this.cursa = cursa;
    }

    /**
     * @return the cursas
     */
    public LinkedList<Cursa> getCursas() {
        if(cursas.isEmpty()) cursas = listall();
        return cursas;
    }

    /**
     * @param cursas the cursas to set
     */
    public void setCursas(LinkedList<Cursa> cursas) {
        this.cursas = cursas;
    }
    public static void main(String[] args) {
        CursaController cc = new CursaController();
//        cc.getCursa().setId(cc.generated_id());
//        cc.getCursa().setId_docente(2000);
//        cc.getCursa().setId_materia(5000);
//        cc.getCursa().setId_matricula(3000);
//        cc.guardar();
//        System.out.println(cc.listall().print());
//        cc.setCursa(null);
//        CursaController cc = new CursaController();
//        cc.getCursa().setId(cc.generated_id());
//        cc.getCursa().setId_docente(2001);
//        cc.getCursa().setId_materia(5001);
//        cc.getCursa().setId_matricula(3001);
//        cc.guardar();
//        System.out.println(cc.listall().print());
//        cc.setCursa(null);
//        cc.getCursa().setId(cc.generated_id());
//        cc.getCursa().setId_docente(2002);
//        cc.getCursa().setId_materia(5002);
//        cc.getCursa().setId_matricula(3002);
//        cc.guardar();
//        System.out.println(cc.listall().print());
//        cc.setCursa(null);


        System.out.println(cc.quickSort(cc.getCursas(), 0, "id").print());
    }
}
