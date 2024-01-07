package com.alura.tienda.prueba;

import com.alura.tienda.dao.PedidoDao;
import com.alura.tienda.modelo.Pedido;
import com.alura.tienda.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.io.FileNotFoundException;

public class PruebaDesempenho {
    public static void main(String[] args) throws FileNotFoundException {
        LoadRecords.cargarRegistros();
        EntityManager em = JPAUtils.getEntityManager();
        Pedido pedido = em.find(Pedido.class, 3L);
//        System.out.println(pedido.getFecha());
//        System.out.println(pedido.getItems().size());
        PedidoDao pedidoDao = new PedidoDao(em);
        Pedido pedidoConCliente = pedidoDao.consultarPedidoConCliente(2L);
        em.close();
        System.out.println(pedidoConCliente.getCliente().getNombre());
    }
}
