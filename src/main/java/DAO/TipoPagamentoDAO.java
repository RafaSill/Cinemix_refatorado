package DAO;

import EntityManager.JPAUtil;
import Persistence.TipoPagamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

public class TipoPagamentoDAO {

    public List<TipoPagamento> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT t FROM TipoPagamento t");
            return consulta.getResultList();
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
}


