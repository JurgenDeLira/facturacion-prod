package com.jorge.facturacion_prod.service;


import com.jorge.facturacion_prod.repository.FacturaRepository;
import com.jorge.facturacion_prod.repository.ProductoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;
    private final ProductoRepository productoRepository;
    private final ModelMapper modelMapper;

    //Inyectar atributos de la clase mediante este constructor:
    public FacturaService(FacturaRepository facturaRepository, ProductoRepository productoRepository, ModelMapper modelMapper) {
        this.facturaRepository = facturaRepository;
        this.productoRepository = productoRepository;
        this.modelMapper = modelMapper;
    }


}
