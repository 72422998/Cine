package pe.com.cine.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.com.cine.dto.ConfiteriaDTO;

public interface ConfiteriaService {
    Page<ConfiteriaDTO> findAll(Pageable pageable);

    ConfiteriaDTO findById(Long id);

    ConfiteriaDTO add(ConfiteriaDTO confiteriaDTO);

    ConfiteriaDTO update(Long id, ConfiteriaDTO confiteriaDTO);

    void delete(Long id);
}
