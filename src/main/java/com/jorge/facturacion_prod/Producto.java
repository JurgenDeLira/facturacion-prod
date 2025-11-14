package com.jorge.facturacion_prod;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor //Constructor vacío
@AllArgsConstructor // Constructor con todas las varíables
public class Producto {
    private Integer id;
    private String nombre;

}
