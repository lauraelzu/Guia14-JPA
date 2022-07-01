package libreria.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAO<T> {

    //cambiar el nombre de la UNIDAD DE PERSISTENCIA  (ProyectoPU)
    protected final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("LibreriaPU");
    protected EntityManager em = EMF.createEntityManager();
    
    protected void conectar() {
        if (!em.isOpen()) {
            em = EMF.createEntityManager();
        }
    }

    protected void desconectar() {
        if (em.isOpen()) {
            em.close();
        }
    }
    
    protected void guardar(T objeto){
        conectar();
        try{
            em.getTransaction().begin();
            em.persist(objeto);
            em.getTransaction().commit(); 
        }catch (Exception e){
            em.getTransaction().rollback(); //en caso de error para que la BD siga siendo consistente!!!
            System.out.println("No se guardó lo último ingresado"); //autor, editorial o libro
        }
        
        desconectar();
    }
    
    protected void editar(T objeto){
        conectar();
        em.getTransaction().begin();
        em.merge(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    protected void eliminar(T objeto){
        conectar();
        em.getTransaction().begin();
        T objeto1 = em.merge(objeto);   //si eliminar tira error, agregar esta línea 
        em.remove(objeto1);             //pasar como parámetro el objeto1 
        em.getTransaction().commit();
        desconectar();
    }

}
