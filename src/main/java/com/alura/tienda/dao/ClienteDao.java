package com.alura.tienda.dao;

import com.alura.tienda.modelo.Cliente;


import javax.persistence.EntityManager;
import java.util.List;


public class ClienteDao {
    private final EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }
    public void guardar(Cliente cliente){
        this.em.persist(cliente);
    }
    public void actualizar(Cliente cliente){
        this.em.merge(cliente);
    }
    public  void remover(Cliente cliente){
        cliente = this.em.merge(cliente);
        this.em.remove(cliente);

    }

    public List<Cliente> consultarTodos() {
       String jpql = "SELECT c FROM Cliente c";
       return em.createQuery(jpql,Cliente.class).getResultList();
    }
}
