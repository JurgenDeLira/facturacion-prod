package com.jorge.facturacion_prod.service;

import com.jorge.facturacion_prod.dto.ProductoDto;
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
    public ProductoDto save (ProductoDto productoDto){
        Producto producto = modelMapper.map(productoDto, Producto.class); //transformamos DTO a producto
        return modelMapper.map(productoRepository.save(producto), ProductoDto.class)

    }

    //find all
    public List<ProductoDto> findAll(){
        return productoRepository.findAll().stream().map(
                producto -> {
                    return modelMapper.map(producto, ProductoDto.class);
                }
        ).collect(Collectors.toList());
    }

    //find by id
    public Optional<ProductoDto> findById(Integer id){
        return productoRepository.findById(id).map(
                producto -> {
                    return modelMapper.map(producto, ProductoDto.class);
                }
        );
    }

    //deleteById
    public boolean deleteBy (Integer id){
        return productoRepository.findById(id).map(
                producto -> {
                    productoRepository.delete(producto);
                    return true;
                }
        ).orElse(false);
    }

    //update
    public Optional<ProductoDto> update(ProductoDto productoDto){
        Producto producto = modelMapper.map(productoDto, Producto.class);
        return productoRepository.findById(producto.getId()).map(
                productoDB -> {
                    return modelMapper.map(productoRepository.save(producto), ProductoDto.class);
                }
        );

    }
}
