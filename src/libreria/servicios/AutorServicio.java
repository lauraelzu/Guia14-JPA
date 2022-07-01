/*
Crear los métodos para dar de alta/bajo o editar Autor.
Búsqueda de un Autor por nombre
 */
package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.persistencia.AutorDAO;

public class AutorServicio {
    private AutorDAO dao;
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    Menus m = new Menus();

    public AutorServicio() {
        this.dao = new AutorDAO();
    }
    
    public Autor crearAutor() {
        Autor autor = new Autor();
        try {
            System.out.println("Ingrese nombre del autor");
            autor.setNombre(leer.next());
            autor.setAlta(true);
            dao.guardar(autor);
            return autor;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
     
    
    public void listarAutores() {
        try {
            List<Autor> autores = dao.listarTodos();
            for (Autor aux : autores) {
                System.out.println(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public Autor buscarPorId() {
        try {
            System.out.println("Ingresar el Id del autor buscado");
            return dao.buscarPorId(leer.nextInt());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    } 
    
    public Autor buscarPorNombre() {
        try {
            System.out.println("Ingresar el nombre del autor buscado");
            return dao.buscarPorNombre(leer.next());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    } 
     
    
    public boolean modificarNombreAutor() {
        try {
            System.out.println("Ingresar el id del autor");
            int id = leer.nextInt();
            System.out.println("Ingresar el nombre correcto del autor");
            String nombreNuevo = leer.next();
            dao.editar(id, nombreNuevo);
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean eliminarPorId() {
        try {
            System.out.println("Ingresar el id del autor");
            dao.eliminar(leer.nextInt());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    
    
    public void menuAutor(){
        
        int opcion = 0;
        do{
            System.out.println("1 Crear autor");
            System.out.println("2 Buscar un autor por su nombre");
            System.out.println("3 Listado de autores");
            System.out.println("4 Modificar el nombre de un autor");
            System.out.println("5 Eliminar un autor según su id"); 
            System.out.println("6 Volver al menú anterior");
            opcion = leer.nextInt();
        }while (opcion>6);
        
        switch(opcion){
            case 1:
                crearAutor();
                break;
            case 2:
                System.out.println(buscarPorNombre());
                break;
            case 3:
                listarAutores();
                break;
            case 4:
                modificarNombreAutor();
                break;
            case 5:
                eliminarPorId();
                break;
            case 6:
                m.mostrarMenu();
                break;
        }
        if ( opcion < 6 ){
           menuAutor(); 
        }
        

    }
     
     
}
