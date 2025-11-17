package com.jorge.facturacion_prod.service;

import com.jorge.facturacion_prod.repository.ProductoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ModelMapper modelMapper;

    // Inyección de dependencias ProductoRepository y ModelMapper
    public ProductoService(ProductoRepository productoRepository, ModelMapper modelMapper) {
        this.productoRepository = productoRepository;
        this.modelMapper = modelMapper;
    }
}
