package DAO;

import EntityManager.JPAUtil;
import Persistence.ComprarFilmes;
import Persistence.Filmes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class FilmesDAO {

    public void cadastrarFilme(Filmes filmes) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(filmes);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    public List<Filmes> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT f FROM Filmes f");
            List<Filmes> filmes = consulta.getResultList();
            return filmes;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    public List<Filmes> filtrarTitulo(String titulo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            CriteriaBuilder cBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Filmes> cQuery = cBuilder.createQuery(Filmes.class);
            Root<Filmes> from = cQuery.from(Filmes.class);

            cQuery.select(from).where(
                    cBuilder.like(
                            cBuilder.lower(from.get("titulo")),
                            "%" + titulo.toLowerCase() + "%"
                    )
            );

            //executando a consulta
            TypedQuery<Filmes> consulta = em.createQuery(cQuery);
            return consulta.getResultList();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }    
    
}
