package com.alura.tienda.dao;

import com.alura.tienda.modelo.Categoria;
import com.alura.tienda.modelo.Producto;


import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ProductoDao {
    private final EntityManager em;

    public ProductoDao(EntityManager em) {
        this.em = em;
    }
    public void guardar(Producto producto){
        this.em.persist(producto);
    }
    public void actualizar(Producto producto){
        this.em.merge(producto);
    }
    public  void remover(Producto producto){
        producto = this.em.merge(producto);
        this.em.remove(producto);

    }
    public Producto consultaPorId(Long id) {
        return em.find(Producto.class, id);
    }
    public List<Producto> consultaPorNombre(String nombre){
        String jpql = "SELECT P FROM Producto AS P WHERE P.nombre =:nombre";

        return em.createQuery(jpql,Producto.class).setParameter("nombre", nombre).getResultList();

    }
    public  List<Producto> consultaPorNombreDeCategoria(String nombre){
        String jpql = "SELECT P FROM Producto AS P  WHERE P.categoria.nombre=:nombre";
        return em.createQuery(jpql,Producto.class).setParameter("nombre", nombre).getResultList();
    }
    public BigDecimal consultarPrecioPorNombreDeProducto(String nombre){
        String jpql = "SELECT P.precio FROM  Producto AS P WHERE P.nombre=:nombre";
        return em.createQuery(jpql,BigDecimal.class).setParameter("nombre",nombre).getSingleResult();
    }
}
