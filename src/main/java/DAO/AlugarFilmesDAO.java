package DAO;

import EntityManager.JPAUtil;
import Persistence.AlugarFilmes;
import jakarta.persistence.EntityManager;

public class AlugarFilmesDAO {
    
    public void salvarAluguel(AlugarFilmes alugar) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            alugar.setUsers(em.merge(alugar.getUsers()));
            alugar.setFilmes(em.merge(alugar.getFilmes()));
            alugar.setTipoPagamento(em.merge(alugar.getTipoPagamento()));

            em.persist(alugar);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
    
}
