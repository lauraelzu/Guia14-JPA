package libreria.servicios;

import java.util.Scanner;

public class Menus {
    
    public void mostrarMenu() {
    
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        int opcion = 0;
        
        AutorServicio as = new AutorServicio();
        EditorialServicio es = new EditorialServicio();
        LibroServicio ls = new LibroServicio();
        
        System.out.println("*******Sistema de guardado de libros*********");
        do{
          System.out.println("1 Menú Autor - 2 Menú Editorial - 3 Menú Libro - 4 Salir");
          opcion = leer.nextInt();
        }while(opcion!=1 && opcion != 2 && opcion != 3 && opcion != 4);
        
        switch (opcion){
            case 1:
                as.menuAutor();
                break;
            case 2:
                es.menuEditorial();
                break;
            case 3:
                ls.menuLibro();
                break;
        }
    }
    
}
