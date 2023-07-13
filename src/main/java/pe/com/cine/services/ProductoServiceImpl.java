package pe.com.cine.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cine.dto.ProductoDTO;
import pe.com.cine.entities.Producto;
import pe.com.cine.repositories.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductoDTO> findAll() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream().map(producto -> modelMapper.map(producto, ProductoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO findById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + id));
        return modelMapper.map(producto, ProductoDTO.class);
    }

    @Override
    public ProductoDTO add(ProductoDTO productoDTO) {
        Producto producto = modelMapper.map(productoDTO, Producto.class);
        Producto productoGuardado = productoRepository.save(producto);
        return modelMapper.map(productoGuardado, ProductoDTO.class);
    }

    @Override
    public ProductoDTO update(Long id, ProductoDTO productoDTO) {
        Producto productoexistente = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID:" + id));
        modelMapper.map(productoDTO, productoexistente);
        Producto productoActualizado = productoRepository.save(productoexistente);
        return modelMapper.map(productoActualizado, ProductoDTO.class);
    }

    @Override
    public void delete(Long id) {
        productoRepository.deleteById(id);
    }

}
