package pe.com.cine.services;

import java.util.List;

import pe.com.cine.dto.ConfiteriaDTO;

public interface ConfiteriaService {
    List<ConfiteriaDTO> findAll();

    ConfiteriaDTO findById(Long id);

    ConfiteriaDTO add(ConfiteriaDTO confiteriaDTO);

    ConfiteriaDTO update(Long id, ConfiteriaDTO confiteriaDTO);

    void delete(Long id);
}
