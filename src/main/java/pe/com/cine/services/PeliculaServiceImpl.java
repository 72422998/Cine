package pe.com.cine.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cine.dto.PeliculaDTO;
import pe.com.cine.entities.Pelicula;
import pe.com.cine.repositories.PeliculaRepository;
@Service
public class PeliculaServiceImpl implements PeliculaService{
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PeliculaDTO> findAll() {
        List<Pelicula> peliculas  = peliculaRepository.findAll();
        return peliculas.stream().map(pelicula -> modelMapper.map(pelicula, PeliculaDTO.class)).collect(Collectors.toList());
    }

    @Override
    public PeliculaDTO findById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public PeliculaDTO add(PeliculaDTO peliculaDTO) {
        Pelicula pelicula = modelMapper.map(peliculaDTO, Pelicula.class);
        Pelicula peliculaGuardada = peliculaRepository.save(pelicula);
        return modelMapper.map(peliculaGuardada, PeliculaDTO.class);
    }

    @Override
    public PeliculaDTO update(long id, PeliculaDTO peliculaDTO) {
        Pelicula peliculaexistente = peliculaRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Pelicula no encontrada con ID: " +id));
        modelMapper.map(peliculaDTO, peliculaexistente);
        Pelicula peliculaActualizada = peliculaRepository.save(peliculaexistente);
        return modelMapper.map(peliculaActualizada, PeliculaDTO.class);
    }

    @Override
    public void delete(long id) {
        peliculaRepository.deleteById(id);
    }
    
    
}
