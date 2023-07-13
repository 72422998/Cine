package pe.com.cine.services;

import java.util.List;

import pe.com.cine.dto.FuncionDTO;

public interface FuncionService {
    List<FuncionDTO>findAll();
    FuncionDTO findById(Long id);
    FuncionDTO add(FuncionDTO funcionDTO);
    FuncionDTO update(Long id, FuncionDTO funcionDTO);
    void delete(Long id);
}
