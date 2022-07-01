package libreria.persistencia;

import java.util.List;
import libreria.entidades.Autor;

public class AutorDAO extends DAO<Autor>{
    
    @Override
    public void guardar(Autor autor) {
        super.guardar(autor);
    }
    
    public List<Autor> listarTodos() throws Exception {
        conectar();
        List<Autor> autores = em.createQuery("SELECT a FROM Autor a").getResultList();
        desconectar();
        return autores;
    }
    
    public Autor buscarPorId(int id) throws Exception {
        conectar();
        Autor autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.id = :id").setParameter("id", id).getSingleResult();
        desconectar();
        return autor;
    }
    
    
    public Autor buscarPorNombre(String nombre) throws Exception {
        conectar();
        Autor autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.nombre like :nombre").setParameter("nombre", nombre).getSingleResult();
        desconectar();
        return autor;
    }
    
    public void editar(int id, String nombre) throws Exception {
        Autor autor = buscarPorId(id);
        autor.setNombre(nombre);
        super.editar(autor);
    }
    
    public void eliminar(int id) throws Exception {
        Autor autor = buscarPorId(id);
        super.eliminar(autor);
    }
    
}
