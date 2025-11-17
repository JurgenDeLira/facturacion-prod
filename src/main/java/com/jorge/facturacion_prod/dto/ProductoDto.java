package com.jorge.facturacion_prod.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data //crea métodos getter y setter y sobreescribe toString
public class ProductoDto {

    private Integer id;
    private String nombre;
    private String detalle;
    private BigDecimal precio;
}
