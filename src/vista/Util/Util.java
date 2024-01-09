/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.Util;

import controlador.TDALista.LinkedList;
import modelo.Docente;
import modelo.Estudiante;

/**
 *
 * @author sakotaz
 */
public class Util {
    public static LinkedList <Estudiante> retornar(Estudiante e) {
        LinkedList <Estudiante> lista = new LinkedList<>();
        lista.add(e);
        return lista;
    }
    
    public static LinkedList <Docente> retornarDocente (Docente d) {
        LinkedList <Docente> lista = new LinkedList<>();
        lista.add(d);
        return lista;
    }
}
