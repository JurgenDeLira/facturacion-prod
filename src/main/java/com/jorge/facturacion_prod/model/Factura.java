package com.jorge.facturacion_prod.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Setter
@Getter
public class Factura {

    private Integer id;
    private String numeroFactura;
    private BigDecimal subTotal;
    private BigDecimal total;

    private final double IVA = 0.15; // 15% iva

    private LocalDateTime fechaCreado;
}
