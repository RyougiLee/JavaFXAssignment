package dao;

import entity.Currency;
import model.ConverterDB;
import java.sql.*;
import java.util.*;
import jakarta.persistence.EntityManager;
import entity.*;

public class ConverterDao {

    public List<Currency> getAllCurrencies() {
        EntityManager em = datasource.MariaDbJpaConnection.getInstance();
        List<Currency> emps = em.createQuery("select c from Currency c").getResultList();
        return emps;
    }

    public void persist(Currency currency){
        EntityManager em = datasource.MariaDbJpaConnection.getInstance();
        em.getTransaction().begin();
        em.persist(currency);
        em.getTransaction().commit();
    }
}
