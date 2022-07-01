package libreria.persistencia;

import java.util.List;
import libreria.entidades.Editorial;

public class EditorialDAO extends DAO<Editorial>{
    @Override
    public void guardar(Editorial editorial) {
        super.guardar(editorial);
    }
    
 
    public List<Editorial> listarTodos() throws Exception {
        conectar();
        List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e").getResultList();
        desconectar();
        return editoriales;
    }
    
    public Editorial buscarPorId(int id) throws Exception {
        conectar();
        Editorial editorial = (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.id = :id").setParameter("id", id).getSingleResult();
        desconectar();
        return editorial;
    }
    
   
    public void editar(int id, String nombre) throws Exception {
        Editorial editorial = buscarPorId(id);
        editorial.setNombre(nombre);
        super.editar(editorial);
    }
    
    public void eliminar(int id) throws Exception {
        Editorial editorial = buscarPorId(id);
        super.eliminar(editorial);
    }
    
}
