package com.jorge.facturacion_prod.service;

import com.jorge.facturacion_prod.dto.ProductoDTO;
import com.jorge.facturacion_prod.model.Producto;
import com.jorge.facturacion_prod.repository.ProductoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ModelMapper modelMapper;

    // Inyección de dependencias ProductoRepository y ModelMapper
    public ProductoService(ProductoRepository productoRepository, ModelMapper modelMapper) {
        this.productoRepository = productoRepository;
        this.modelMapper = modelMapper;
    }

    //save
    public ProductoDTO save (ProductoDTO productoDto){
        Producto producto = modelMapper.map(productoDto, Producto.class); //transformamos DTO a producto
        return modelMapper.map(productoRepository.save(producto), ProductoDTO.class);

    }

    //find all
    public List<ProductoDTO> findAll(){
        return productoRepository.findAll().stream().map(
                producto -> {
                    return modelMapper.map(producto, ProductoDTO.class);
                }
        ).collect(Collectors.toList());
    }

    //find by id
    public Optional<ProductoDTO> findById(Integer id){
        return productoRepository.findById(id).map(
                producto -> {
                    return modelMapper.map(producto, ProductoDTO.class);
                }
        );
    }

    //deleteById
    public boolean deleteById(Integer id){
        return productoRepository.findById(id).map(
                producto -> {
                    productoRepository.delete(producto);
                    return true;
                }
        ).orElse(false);
    }

    //update
    public Optional<ProductoDTO> update(ProductoDTO productoDto){
        Producto producto = modelMapper.map(productoDto, Producto.class);
        return productoRepository.findById(producto.getId()).map(
                productoDB -> {
                    return modelMapper.map(productoRepository.save(producto), ProductoDTO.class);
                }
        );

    }
}
