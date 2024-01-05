package com.alura.tienda.prueba;

import com.alura.tienda.modelo.Producto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class RegistroDeProducto {
    public static void main(String[] args) {
        Producto celular  = new Producto();
        celular.setNombre("Samsung");
        celular.setDescripcion("Telefono usado");
        celular.setPrecio(new BigDecimal("1000"));
        EntityManagerFactory factory  = Persistence.createEntityManagerFactory("tienda");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.persist(celular);
    }
}
