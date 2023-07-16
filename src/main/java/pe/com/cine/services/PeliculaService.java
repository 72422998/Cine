package pe.com.cine.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.com.cine.dto.PeliculaDTO;

public interface PeliculaService {
    Page<PeliculaDTO>findAll(Pageable pageable);
    PeliculaDTO findById(Long id);
    PeliculaDTO add(PeliculaDTO peliculaDTO);
    PeliculaDTO update(Long id, PeliculaDTO peliculaDTO);
    void delete(Long id);
}
