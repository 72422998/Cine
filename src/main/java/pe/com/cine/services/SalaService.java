package pe.com.cine.services;

import java.util.List;

import pe.com.cine.dto.SalaDTO;

public interface SalaService {
    List<SalaDTO>findAll();
    SalaDTO findById(Long id);
    SalaDTO add(SalaDTO salaDTO);
    SalaDTO update(Long id, SalaDTO salaDTO);
    void delete(Long id);


    
}
