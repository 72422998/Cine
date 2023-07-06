package pe.com.cine.services;

import java.util.List;

import pe.com.cine.dto.CategoriaDTO;

public interface CategoriaService {
    List<CategoriaDTO>findAll();
    CategoriaDTO findById(long id);
    CategoriaDTO add(CategoriaDTO categoriaDTO);
    CategoriaDTO update(long id,CategoriaDTO categoriaDTO);
    void delete(long id);
}
