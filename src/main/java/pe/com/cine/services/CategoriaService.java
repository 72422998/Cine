package pe.com.cine.services;

import java.util.List;

import pe.com.cine.dto.CategoriaDTO;

public interface CategoriaService {
    List<CategoriaDTO>findAll();
    CategoriaDTO findById(Long id);
    CategoriaDTO add(CategoriaDTO categoriaDTO);
    CategoriaDTO update(Long id,CategoriaDTO categoriaDTO);
    void delete(Long id);
}
