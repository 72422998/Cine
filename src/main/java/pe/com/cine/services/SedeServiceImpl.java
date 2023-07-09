package pe.com.cine.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cine.dto.SedeDTO;
import pe.com.cine.entities.Sede;
import pe.com.cine.repositories.SedeRepository;

@Service
public class SedeServiceImpl implements
SedeService{
    @Autowired
    private SedeRepository sedeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<SedeDTO> findAll() {
        List<Sede> sede = sedeRepository.findAll();
        return sede.stream().map(Sede->modelMapper.map
        (sede, SedeDTO.class)).collect(Collectors.toList());
    }
    @Override
    public SedeDTO findById(Long id) {
        Sede sede = sedeRepository.
        findById(id).orElseThrow(()->new 
        IllegalArgumentException("Sede no encontrada:" + id));
        return modelMapper.map(sede, SedeDTO.class);

    }
    @Override
    public SedeDTO and(SedeDTO sedeDTO) {
        Sede sede = modelMapper.map
        (sedeDTO, Sede.class);
        Sede sedeGuardada = sedeRepository.
        save(sede);
        return modelMapper.map(sedeGuardada,
        SedeDTO.class);
       
    }
    @Override
    public SedeDTO update(Long id, SedeDTO sedeDTO) {
        Sede sedeexistente =
        sedeRepository.findById(id).orElseThrow(()
        -> new IllegalArgumentException("Sede no Encontrada con id:" + id));
        modelMapper.map(sedeDTO,
        sedeexistente);
        Sede sedeActualizada = 
        sedeRepository.save(sedeexistente);
        return modelMapper.map(sedeActualizada ,
        SedeDTO.class);
    }
    @Override
    public void delete(Long id) {
       sedeRepository.deleteById(id);
    }

  

    
    
}
