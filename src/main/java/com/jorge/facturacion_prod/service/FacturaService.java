package com.jorge.facturacion_prod.service;


import com.jorge.facturacion_prod.dto.RequestDetalleFacturaDTO;
import com.jorge.facturacion_prod.dto.RequestFacturaDTO;
import com.jorge.facturacion_prod.dto.ResponseFacturaDTO;
import com.jorge.facturacion_prod.model.DetalleFactura;
import com.jorge.facturacion_prod.model.Factura;
import com.jorge.facturacion_prod.model.Producto;
import com.jorge.facturacion_prod.repository.FacturaRepository;
import com.jorge.facturacion_prod.repository.ProductoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    @Transactional
    public ResponseFacturaDTO save(RequestFacturaDTO requestFacturaDTO) {
        Factura factura = new Factura();
        BigDecimal subtotalFactura = BigDecimal.ZERO;
        Set<DetalleFactura> detalles = new HashSet<>();

        factura.setNumeroFactura(requestFacturaDTO.getNumeroFactura());

        for (RequestDetalleFacturaDTO detalleFacturaDTO: requestFacturaDTO.getDetalleFacturas()){
            //Producto producto = productoRepository.findById(detalleFacturaDTO.getIdProducto()).get();
            Producto producto = productoRepository.findById(detalleFacturaDTO.getIdProducto()).orElseThrow(
                    () -> new RuntimeException(" Producto no encontrado")
            );

            BigDecimal totalProducto = producto.getPrecio().multiply(BigDecimal.valueOf(detalleFacturaDTO.getCantidad()));

            subtotalFactura = subtotalFactura.add(totalProducto);

            DetalleFactura detalleFactura = new DetalleFactura();
            detalleFactura.setIdProducto(detalleFacturaDTO.getIdProducto());
            detalleFactura.setPrecio(producto.getPrecio());
            detalleFactura.setCantidad(detalleFacturaDTO.getCantidad());
            detalleFactura.setTotal(totalProducto);
            detalleFactura.setFactura(factura);

            detalles.add(detalleFactura);
        }

        factura.setDetalleFacturas(detalles);
        factura.setSubTotal(subtotalFactura);
        factura.setTotal(subtotalFactura.add(subtotalFactura
                .multiply(BigDecimal.valueOf(factura.getIVA()))));

        Factura savedFactura = facturaRepository.save(factura);

        return modelMapper.map(savedFactura, ResponseFacturaDTO.class);
    }
}
