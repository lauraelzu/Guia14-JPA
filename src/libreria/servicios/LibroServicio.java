/*
Crear los métodos para dar de alta/bajo o editar Libro.
Búsqueda de un libro por ISBN.
Búsqueda de un libro por Título.
Búsqueda de un libro/s por nombre de Autor.
Búsqueda de un libro/s por nombre de Editorial.
 */
package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Libro;
import libreria.persistencia.LibroDAO;

public class LibroServicio {
    
    private LibroDAO dao;
    
    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public LibroServicio() {
        this.dao = new LibroDAO();
    }

    
    public Libro crearLibro(){
        Libro libro = new Libro();
        AutorServicio as = new AutorServicio();
        EditorialServicio es = new EditorialServicio();
        
        
        try {
            System.out.println("Ingresar el ISBN del libro");
            libro.setIsbn(leer.nextLong());
            System.out.println("Ingresar título del libro");
            libro.setTitulo(leer.next());
            System.out.println("Ingresar el año de edición");
            libro.setAnio(leer.nextInt());
            System.out.println("Ingresar cantidad de ejemplares existentes");
            libro.setEjemplares(leer.nextInt());
//            libro.setEjemplaresPrestados(0);          //
//            libro.setEjemplaresRestantes(ejemplares); //
            libro.setAutor(as.buscarPorId());
            libro.setEditorial(es.buscarPorId());
            dao.guardar(libro);
            return libro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    
    public boolean eliminarPorIsbn() {
        try {
            System.out.println("Ingresar ISBN del libro a eliminar");
            dao.eliminar(leer.nextLong());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public void listarLibros() {
        try {
            List<Libro> libros = dao.listarTodos();
            for (Libro aux : libros) {
                System.out.println(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public Libro buscarPorIsbn() {
        try {
            System.out.println("Ingresar el ISBN del libro buscado");
            return dao.buscarPorIsbn(leer.nextLong());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    } 
     
    
    public Libro buscarPorTitulo() {
        try {
            System.out.println("Ingresar el título del libro buscado");
            return dao.buscarPorTitulo(leer.next());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    } 
    
     public List<Libro> buscarPorAutor() {
        try {
            System.out.println("Ingresar el nombre del autor del libro buscado");
            return dao.buscarPorAutor(leer.next());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    } 
    
     public List<Libro> buscarPorEditorial() {
        try {
            System.out.println("Ingresar la editorial del libro buscado");
            return dao.buscarPorEditorial(leer.next());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    } 
     
    public boolean modificarNombreLibro() {
        try {
            System.out.println("Ingresar el ISBN del libro");
            Long isbn = leer.nextLong();
            System.out.println("Ingresar el nombre correcto del libro");
            String nombreNuevo = leer.next();
            dao.editar(isbn, nombreNuevo);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public void menuLibro(){
        
        Menus m = new Menus();
        
        int opcion = 0;
        do{
            System.out.println("1 Crear libro");
            System.out.println("2 Buscar un libro por su ISBN");
            System.out.println("3 Buscar un libro por su título");
            System.out.println("4 Buscar un libro por su autor");
            System.out.println("5 Buscar un libro por su editorial");
            System.out.println("6 Listado de libros");
            System.out.println("7 Modificar el nombre de un libro");
            System.out.println("8 Eliminar un libro según su ISBN"); 
            System.out.println("9 Volver al menú anterior");
            opcion = leer.nextInt();
        }while (opcion>9);
        
        switch(opcion){
            case 1:
                crearLibro();
                break;
            case 2:
                System.out.println(buscarPorIsbn());
                break;
            case 3:
                System.out.println(buscarPorTitulo());
                break;
            case 4:
                for (Libro aux : buscarPorAutor()) {
                    System.out.println(aux);    
                }
                break;
            case 5:
                
                for (Libro aux : buscarPorEditorial()) {
                    System.out.println(aux);
                }
                break;
            case 6:
                listarLibros();
                break;
            case 7:
                modificarNombreLibro();
                break;
            case 8:
                eliminarPorIsbn();
                break;
            case 9:
                m.mostrarMenu();
                break;
        }
        if ( opcion < 9 ) {
           menuLibro(); 
        }

    }
}
