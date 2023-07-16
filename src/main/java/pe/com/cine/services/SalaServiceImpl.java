package pe.com.cine.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cine.dto.PeliculaDTO;
import pe.com.cine.dto.SalaDTO;
import pe.com.cine.entities.Categoria;
import pe.com.cine.entities.Pelicula;
import pe.com.cine.entities.Sala;
import pe.com.cine.entities.Sede;
import pe.com.cine.repositories.SalaRepository;
import pe.com.cine.repositories.SedeRepository;

@Service
public class SalaServiceImpl implements SalaService {
    @Autowired
    private SalaRepository salaRepository;
   
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SedeRepository sedeRepository;

    @Override
    public List<SalaDTO> findAll() {
       List<Sala> salas = salaRepository.findAll();
       return salas.stream().map(sala -> modelMapper.map(sala,SalaDTO.class)).collect(Collectors.toList());
    }


    @Override
    public SalaDTO findById(Long id) {
       Sala sala=salaRepository.findById
       (id).orElseThrow(()-> new IllegalArgumentException("Sala No Encontrada :"+ id));
       return modelMapper.map(sala,SalaDTO.class);
    }

    @Override
    public SalaDTO add(SalaDTO salaDTO) {
       Sala sala =modelMapper.map(salaDTO, Sala.class);
       Sede sede = sedeRepository.findById(salaDTO.getSede().getSedeId()).orElseThrow(()-> new IllegalArgumentException("Sede no encontrada con Id: " + salaDTO.getSede().getSedeId()));
       sala.setSede(sede);
       Sala salaGuardada = salaRepository.save(sala);
       return modelMapper.map(salaGuardada,SalaDTO.class);
    }
    // @Override
    // public PeliculaDTO add(PeliculaDTO peliculaDTO) {
    //     Pelicula pelicula = modelMapper.map(peliculaDTO, Pelicula.class);

    //     // Obtener la categoria por su ID y establecerla en la pelicula
    //     Categoria categoria = categoriaRepository.findById(peliculaDTO.getCategoria().getCategoriaId()).orElseThrow(() -> new IllegalArgumentException("Categoria no encontrada cond ID: " + peliculaDTO.getCategoria().getCategoriaId()));
    //     pelicula.setCategoria(categoria);

    //     Pelicula peliculaGuardada = peliculaRepository.save(pelicula);
    //     return modelMapper.map(peliculaGuardada, PeliculaDTO.class);
    // }

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
