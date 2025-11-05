package com.example.servidorapi.model.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import jakarta.persistence.*;


import java.math.BigDecimal;

@Entity

@Table(name = "Products")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private Integer idProducto;

    @Column(name = "ProductName")
    private String nombreProducto;

    @Column(name = "SupplierID")
    private Integer idProveedor;

    @Column(name = "CategoryID")
    private Integer idCategoria;

    @Column(name = "Unit")
    private String unidad;

    @Column(name = "Price")

    private BigDecimal precio;

    public Product() {}

    public Product(String nombreProducto, Integer idProveedor, Integer idCategoria, String unidad, BigDecimal precio) {
        this.nombreProducto = nombreProducto;

        this.idProveedor = idProveedor;

        this.idCategoria = idCategoria;

        this.unidad = unidad;


        this.precio = precio;
    }


    public Integer getIdProducto() {
        return idProducto;

    }
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;

    }

    public String getNombreProducto() {
        return nombreProducto;
    }
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }
    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;

    }

    public Integer getIdCategoria() {
        return idCategoria;
    }
    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;

    }

    public String getUnidad() {
        return unidad; }


    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }



    public BigDecimal getPrecio() {
        return precio;


    }
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}