/*
administrar las operaciones CRUD (Create, Remove, Update, Delele)
 */
package libreria.persistencia;

import java.util.List;
import libreria.entidades.Libro;

public class LibroDAO extends DAO<Libro>{
    @Override
    public void guardar(Libro libro) {
        super.guardar(libro);
    }
    
    
    public void eliminar(Long isbn) throws Exception {
        Libro libro = buscarPorIsbn(isbn);
        super.eliminar(libro);
    }
    
    public Libro buscarPorIsbn(Long isbn) throws Exception {
        conectar();
        Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.isbn = :isbn").setParameter("isbn", isbn).getSingleResult();
        desconectar();
        return libro;
    }
    
    public Libro buscarPorTitulo(String titulo) throws Exception {
        conectar();
        Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.titulo like :titulo").setParameter("titulo", titulo).getSingleResult();
        desconectar();
        return libro;
    }
    
    public List<Libro> buscarPorAutor(String autor) throws Exception {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l "
                + "WHERE l.autor.nombre like :autor").setParameter("autor", autor).
                getResultList();
        desconectar();
        return libros;
    }
    
    public List<Libro> buscarPorEditorial(String editorial) throws Exception {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l "
                + "WHERE l.editorial.nombre like :editorial")
                .setParameter("editorial", editorial).getResultList();
        desconectar();
        return libros;
    }
   
    public void editar(Long isbn, String titulo) throws Exception {
        Libro libro = buscarPorIsbn(isbn);
        libro.setTitulo(titulo);
        super.editar(libro);
    }



    public List<Libro> listarTodos() throws Exception {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l ").getResultList();
        desconectar();
        return libros;
    }


    
}
