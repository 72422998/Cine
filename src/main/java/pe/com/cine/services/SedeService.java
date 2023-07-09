package pe.com.cine.services;

import java.util.List;

import pe.com.cine.dto.SedeDTO;



public interface SedeService {
    List<SedeDTO>findAll();
   SedeDTO findById(Long id);
   SedeDTO and(SedeDTO sedeDTO);
   SedeDTO update(Long id , SedeDTO sedeDTO);
   void delete(Long id);
    
}
