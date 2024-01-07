package com.alura.tienda.prueba;

import com.alura.tienda.dao.CategoriaDao;
import com.alura.tienda.dao.ClienteDao;
import com.alura.tienda.dao.PedidoDao;
import com.alura.tienda.dao.ProductoDao;
import com.alura.tienda.modelo.*;
import com.alura.tienda.utils.JPAUtils;
import com.alura.tienda.vo.RelatoriaDeVenta;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;


public class RegistroDePedido {
    public static void main(String[] args) {
        registrarProducto();
        EntityManager entityManager =  JPAUtils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(entityManager);
        Producto producto = productoDao.consultaPorId(1L);
        BigDecimal prueba = productoDao.consultarPrecioPorNombreDeProducto("Xiaomi Redmi");
        System.out.println("prueba de consulta en la entidad el valor es: " + prueba);

        Cliente cliente = new Cliente("Juan","119312332");
        Pedido pedido = new Pedido(cliente);
        pedido.agregarItems(new ItemsPedido(5,producto,pedido));
        ClienteDao clienteDao = new ClienteDao(entityManager);
        PedidoDao pedidoDao = new  PedidoDao(entityManager);
        entityManager.getTransaction().begin();
        clienteDao.guardar(cliente);
        pedidoDao.guardar(pedido);
        entityManager.getTransaction().commit();
        BigDecimal valorTotal = pedidoDao.valorTotalVendido();
        System.out.println("El valor total es "+valorTotal);
        List<RelatoriaDeVenta>  relatorio = pedidoDao.relatorioDeVentasVo();
        relatorio.forEach(System.out::println);

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
