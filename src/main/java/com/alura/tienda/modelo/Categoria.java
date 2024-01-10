package com.alura.tienda.modelo;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {
    @EmbeddedId
    private CategoriaId categoriaId;
    public Categoria( String nombre) {
        this.categoriaId = new CategoriaId(nombre,"456");
    }

    public Categoria() {

    }



    public String getNombre() {
        return  categoriaId.getNombre();
    }

    public void setNombre(String nombre) {
        categoriaId.setNombre(nombre);

    }
}
