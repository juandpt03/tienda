package com.alura.tienda.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Libros")
public class Libro extends  Producto{
    private String autor;
    private int paginas;
    public Libro(){}
    public Libro(String autor, int paginas) {
        this.autor = autor;
        this.paginas = paginas;
    }
}
