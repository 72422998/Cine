package pe.com.cine.services;

import java.util.List;

import pe.com.cine.dto.PeliculaDTO;

public interface PeliculaService {
    List<PeliculaDTO>findAll();
    PeliculaDTO findById(Long id);
    PeliculaDTO add(PeliculaDTO peliculaDTO);
    PeliculaDTO update(Long id, PeliculaDTO peliculaDTO);
    void delete(Long id);
}
