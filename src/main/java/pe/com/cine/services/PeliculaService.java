package pe.com.cine.services;

import java.util.List;

import pe.com.cine.dto.PeliculaDTO;

public interface PeliculaService {
    List<PeliculaDTO>findAll();
    PeliculaDTO findById(long id);
    PeliculaDTO add(PeliculaDTO peliculaDTO);
    PeliculaDTO update(long id, PeliculaDTO peliculaDTO);
    void delete(long id);
}
