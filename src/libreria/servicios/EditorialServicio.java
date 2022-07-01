/*
Crear los métodos para dar de alta/bajo o editar Editorial.
 */
package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Editorial;
import libreria.persistencia.EditorialDAO;

public class EditorialServicio {
    private EditorialDAO dao;
    
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    Menus m = new Menus();

    public EditorialServicio() {
        this.dao = new EditorialDAO();
    }
    
    public Editorial crearEditorial() {
        Editorial editorial = new Editorial();
        try {
            System.out.println("Ingresar nombre de la editorial");
            editorial.setNombre(leer.next());
            editorial.setAlta(true);
            dao.guardar(editorial);
            return editorial;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public void listarEditoriales() {
        try {
            List<Editorial> editoriales = dao.listarTodos();
            for (Editorial aux : editoriales) {
                System.out.println(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public Editorial buscarPorId() {
        try {
            System.out.println("Ingresar el Id de la editorial buscada");
            return dao.buscarPorId(leer.nextInt());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    } 
     
    
    public boolean modificarNombreEditorial() {
        try {
            System.out.println("Ingresar el id de la editorial");
            int id = leer.nextInt();
            System.out.println("Ingresar el nombre correcto de la editorial");
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
            System.out.println("Ingresar el id de la editorial");
            dao.eliminar(leer.nextInt());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    
    
    public void menuEditorial(){
        
        int opcion = 0;
        do{
            System.out.println("1 Crear editorial");
            System.out.println("2 Buscar una editorial por su id");
            System.out.println("3 Listado de editoriales");
            System.out.println("4 Modificar el nombre de una editorial");
            System.out.println("5 Eliminar una editorial según su id"); 
            System.out.println("6 Volver al menú anterior");
            opcion = leer.nextInt();
        }while (opcion>6);
        
        switch(opcion){
            case 1:
                crearEditorial();
                break;
            case 2:
                System.out.println(buscarPorId());
                break;
            case 3:
                listarEditoriales();
                break;
            case 4:
                modificarNombreEditorial();
                break;
            case 5:
                eliminarPorId();
                break;
            case 6:
                m.mostrarMenu();
                break;
        }
        if ( opcion < 6 ) {
            menuEditorial();
        }

    }
    
}
