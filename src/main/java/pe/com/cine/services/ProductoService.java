package pe.com.cine.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.com.cine.dto.ProductoDTO;

public interface ProductoService {
    Page<ProductoDTO> findAll(Pageable pageable);

    ProductoDTO findById(Long id);

    ProductoDTO add(ProductoDTO productoDTO);

    ProductoDTO update(Long id, ProductoDTO productoDTO);

    void delete(Long id);
}
