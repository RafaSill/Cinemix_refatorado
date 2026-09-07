package DAO;

import Persistence.Users;
import EntityManager.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class UserDAO {
    
    public void cadastrar(Users user){
        EntityManager em = JPAUtil.getEntityManager();
                
        try{
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch(Exception e){
            em.getTransaction().rollback();
            throw e;
        }
        finally{
            JPAUtil.closeEntityManager();
        }
    }
    
    public Users validarLogin(String email, String senha){
        EntityManager em = JPAUtil.getEntityManager();
        
        try{
            String jpql = "SELECT u FROM Users u WHERE u.email = :email AND u.senha = :senha";
            TypedQuery<Users> query = em.createQuery(jpql, Users.class);
            query.setParameter("email", email);
            query.setParameter("senha", senha);
            
            List<Users> lista = query.getResultList();
            
            if (lista.isEmpty()) {
                return null;
            }
            
            return lista.get(0);
            
        } finally {
            em.close();
        }
    }
    
    
}
