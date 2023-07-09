package pe.com.cine.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cine.dto.SalaDTO;
import pe.com.cine.entities.Sala;
import pe.com.cine.repositories.SalaRepository;

@Service
public class SalaServiceImpl implements SalaService {
    @Autowired
    private SalaRepository salaRepository;
   
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SalaDTO> findAll() {
       List<Sala> sala = salaRepository.findAll();
       return sala.stream().map(sede
       ->modelMapper.map(sala,SalaDTO.class)).collect(Collectors.toList());
    }

    @Override
    public SalaDTO findById(Long id) {
       Sala sala=salaRepository.findById
       (id).orElseThrow(()-> new IllegalArgumentException("Sala No Encontrada :"+ id));
       return modelMapper.map(sala,SalaDTO.class);
    }

    @Override
    public SalaDTO add(SalaDTO salaDTO) {
       Sala sala =modelMapper.map
       (salaDTO, Sala.class);
       Sala salaGuardada = salaRepository.
       save(sala);
       return modelMapper.map(salaGuardada,SalaDTO.class);
    }

    @Override
    public SalaDTO update(Long id, SalaDTO salaDTO) {
        Sala salaexistente= salaRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Sala no encontrada por Id:" + id));
        modelMapper.map(salaDTO,salaexistente);
        Sala salaActualizada =
        salaRepository.save(salaexistente);
        return modelMapper.map(salaActualizada,SalaDTO.class);
    }

    @Override
    public void delete(Long id) {
        salaRepository.deleteById(id);
    }
    
}
