package pe.com.cine.services;

import java.util.List;

import pe.com.cine.dto.ProductoDTO;

public interface ProductoService {
    List<ProductoDTO> findAll();

    ProductoDTO findById(Long id);

    ProductoDTO add(ProductoDTO productoDTO);

    ProductoDTO update(Long id, ProductoDTO productoDTO);

    void delete(Long id);
}
