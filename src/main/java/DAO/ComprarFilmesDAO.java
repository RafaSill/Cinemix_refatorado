package DAO;

import EntityManager.JPAUtil;
import Persistence.ComprarFilmes;
import jakarta.persistence.EntityManager;

public class ComprarFilmesDAO {

    public void salvarCompra(ComprarFilmes compra) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            compra.setUsers(em.merge(compra.getUsers()));
            compra.setFilmes(em.merge(compra.getFilmes()));
            compra.setTipoPagamento(em.merge(compra.getTipoPagamento()));

            em.persist(compra);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
}
