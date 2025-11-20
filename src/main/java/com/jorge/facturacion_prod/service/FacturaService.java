package com.jorge.facturacion_prod.service;


import com.jorge.facturacion_prod.dto.ResponseFacturaDTO;
import com.jorge.facturacion_prod.repository.FacturaRepository;
import com.jorge.facturacion_prod.repository.ProductoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;
    private final ProductoRepository productoRepository;
    private final ModelMapper modelMapper;

    //Inyectar atributos de la clase mediante este constructor:
    public FacturaService(FacturaRepository facturaRepository,
                          ProductoRepository productoRepository,
                          ModelMapper modelMapper) {
        this.facturaRepository = facturaRepository;
        this.productoRepository = productoRepository;
        this.modelMapper = modelMapper;
    }

    //Obtener todas las facturas
    public List<ResponseFacturaDTO> findAll() {
        return facturaRepository.findAll().stream().map(
                factura -> modelMapper.map(factura, ResponseFacturaDTO.class)
        ).collect(Collectors.toList());
    }

    //Obtener facturas por id
    public Optional<ResponseFacturaDTO>findById(Integer id){
        return facturaRepository.findById(id).map(
                factura -> modelMapper.map(factura, ResponseFacturaDTO.class)
        );
    }

    //Eliminar las facturas por su id
    public void deleteById(Integer id) {
        facturaRepository.deleteById(id);
    }
}
