package com.alura.tienda.prueba;

import com.alura.tienda.dao.CategoriaDao;
import com.alura.tienda.dao.ProductoDao;
import com.alura.tienda.modelo.Categoria;
import com.alura.tienda.modelo.Producto;
import com.alura.tienda.utils.JPAUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class RegistroDeProducto {
    public static void main(String[] args) {
        registrarProducto();
        EntityManager entityManager =  JPAUtils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(entityManager);


        Producto producto = productoDao.consultaPorId(1L);
        System.out.println(producto.getNombre());

        List<Producto> productos = productoDao.consultaPorNombre("Xiaomi Redmi");
        List<Producto> productos1 = productoDao.consultaPorNombreDeCategoria("CELULARES");
        productos.forEach(producto1 -> System.out.println(producto1.getDescripcion()));
        productos1.forEach(producto1 -> System.out.println(producto1.getNombre()));
        BigDecimal precio = productoDao.consultarPrecioPorNombreDeProducto("Xiaomi Redmi");
        System.out.println(precio);
        List<Producto> productos2 = productoDao.consultarPorParametros("Xiaomi Redmi", null, null);
        String descripcion = productos2.getFirst().getDescripcion();
        System.out.println(descripcion);


    }
    private static void registrarProducto(){
        EntityManager entityManager =  JPAUtils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(entityManager);
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);

        Categoria celulares = new Categoria("CELULARES");
        Producto celular = new Producto("Xiaomi Redmi", "Legal este producto",new BigDecimal("1000"),celulares);

        entityManager.getTransaction().begin();
        categoriaDao.guardar(celulares);
        productoDao.guardar(celular);
        entityManager.getTransaction().commit();
        entityManager.close();


    }
}
