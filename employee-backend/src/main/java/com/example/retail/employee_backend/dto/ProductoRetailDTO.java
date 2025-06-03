package com.example.retail.employee_backend.dto;

import java.util.UUID;

public class ProductoRetailDTO {
    private String idProducto;
    private String nombreProducto;
    public String getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    
}
