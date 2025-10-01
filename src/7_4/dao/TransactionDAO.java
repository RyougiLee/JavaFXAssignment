package dao;

import entity.*;
import jakarta.persistence.EntityManager;

public class TransactionDAO {

    public void persist(Transaction trans){

        EntityManager em = datasource.MariaDbJpaConnection.getInstance();
        em.getTransaction().begin();
        em.persist(trans);
        em.getTransaction().commit();
    }

    public Transaction find(int id){

        EntityManager em = datasource.MariaDbJpaConnection.getInstance();
        Transaction trans = em.find(Transaction.class, id);
        return trans;
    }
}
