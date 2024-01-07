package com.alura.tienda.dao;

import com.alura.tienda.modelo.Pedido;
import com.alura.tienda.vo.RelatoriaDeVenta;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;


public class PedidoDao {
    private final EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }
    public void guardar(Pedido pedido){
        this.em.persist(pedido);
    }
    public void actualizar(Pedido pedido){
        this.em.merge(pedido);
    }
    public  void remover(Pedido pedido){
        pedido = this.em.merge(pedido);
        this.em.remove(pedido);

    }
    public BigDecimal valorTotalVendido(){
        String jpql = "SELECT SUM(P.valorTotal) FROM Pedido P";
        return  em.createQuery(jpql,BigDecimal.class).getSingleResult();
    }
    public Double valorPromedioVendido(){
        String jpql = "SELECT AVG(P.valorTotal) FROM Pedido P";
        return em.createQuery(jpql,Double.class).getSingleResult();
    }
    public List<Object[]> relatorioDeVentas(){
        String jpql = "SELECT producto.nombre, " +
                "SUM(item.cantidad)," +
                "MAX(pedido.fecha) " +
                "FROM Pedido pedido " +
                "JOIN pedido.items item " +
                "JOIN item.producto producto " +
                "GROUP BY producto.nombre " +
                "ORDER BY item.cantidad DESC";
        return em.createQuery(jpql,Object[].class).getResultList();

    } public List<RelatoriaDeVenta> relatorioDeVentasVo(){
        String jpql = "SELECT new  com.alura.tienda.vo.RelatoriaDeVenta(producto.nombre, " +
                "SUM(item.cantidad)," +
                "MAX(pedido.fecha)) " +
                "FROM Pedido pedido " +
                "JOIN pedido.items item " +
                "JOIN item.producto producto " +
                "GROUP BY producto.nombre " +
                "ORDER BY item.cantidad DESC";
        return em.createQuery(jpql,RelatoriaDeVenta.class).getResultList();

    }
    public Pedido consultarPedidoConCliente(Long id){
        String jpql = "SELECT P FROM Pedido P JOIN FETCH P.cliente WHERE P.id=:id";
        return em.createQuery(jpql,Pedido.class).setParameter("id",id).getSingleResult();
    }


}
