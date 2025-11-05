package com.example.clienteapi.model.entity;


import java.math.BigDecimal;

public class Product {

    private Integer idProducto;

    private String nombreProducto;

    private Integer idProveedor;

    private Integer idCategoria;

    private String unidad;

    private BigDecimal precio;

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
        return unidad;

    }

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