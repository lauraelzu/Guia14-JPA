/*
desarrollo de un sistema de guardado de libros en JAVA
utilizando una base de datos MySQL y JPA como framework de persistencia. 
 */
package libreria;

import libreria.servicios.Menus;

public class Libreria {

    public static void main(String[] args) {
        Menus m = new Menus();

        m.mostrarMenu();
        
        
    }
    
}
