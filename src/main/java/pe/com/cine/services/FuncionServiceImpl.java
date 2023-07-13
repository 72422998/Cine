package pe.com.cine.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cine.dto.FuncionDTO;
import pe.com.cine.entities.Funcion;
import pe.com.cine.entities.Pelicula;
import pe.com.cine.entities.Sala;
import pe.com.cine.repositories.FuncionRepository;
import pe.com.cine.repositories.PeliculaRepository;
import pe.com.cine.repositories.SalaRepository;

@Service
public class FuncionServiceImpl implements FuncionService{


    @Autowired
    private FuncionRepository funcionRepository;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<FuncionDTO> findAll() {
        List<Funcion> funciones = funcionRepository.findAll();
        return funciones.stream().map(funcion -> modelMapper.map(funcion, FuncionDTO.class)).collect(Collectors.toList());
    }

    @Override
    public FuncionDTO findById(Long id) {
        Funcion funcion = funcionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Función no encontrado con ID: " + id));
        return modelMapper.map(funcion, FuncionDTO.class);
    }

    @Override
    public FuncionDTO add(FuncionDTO funcionDTO) {
        Funcion funcion = modelMapper.map(funcionDTO, Funcion.class);

        // Obtener la pelicula por su ID y establecerla en la funcion
        Pelicula pelicula = peliculaRepository.findById(funcionDTO.getPelicula().getPeliculaId()).orElseThrow(() -> new IllegalArgumentException("Pelicula no encontrada con ID: " + funcionDTO.getPelicula().getPeliculaId()));

        // Obtener la sala por su ID y establecerla en la funcion
        Sala sala = salaRepository.findById(funcionDTO.getSala().getSalaId()).orElseThrow(() -> new IllegalArgumentException("Sala no encontrada con ID: " + funcionDTO.getSala().getSalaId()));

        funcion.setPelicula(pelicula);
        funcion.setSala(sala);

        Funcion funcionGuardada = funcionRepository.save(funcion);
        return modelMapper.map(funcionGuardada, FuncionDTO.class);
    }

    @Override
    public FuncionDTO update(Long id, FuncionDTO funcionDTO) {
        Funcion funcionExistente = funcionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Funcion no encontrada con ID: " + id));

        // Obtener la pelicula actual de la funcion
        Pelicula peliculaActual = funcionExistente.getPelicula();

        // Obtener la sala actual de la funcion
        Sala salaActual = funcionExistente.getSala();

        // Establecer el identificador de la pelicula actual en el objeto FuncionDTO
        funcionDTO.getPelicula().setPeliculaId(peliculaActual.getPeliculaId());

        // Establecer el identificador de la sala actual en el objeto FuncionDTO
        funcionDTO.getSala().setSalaId(salaActual.getSalaId());

        // Realizar el mapeo de los demás atributos de funcionDTO o funcionExistente
        modelMapper.map(funcionDTO, funcionExistente);

        // Guardar la funcion actualizada en la base de datos
        Funcion funcionActualizada = funcionRepository.save(funcionExistente);

        return modelMapper.map(funcionActualizada, FuncionDTO.class);
    }

    @Override
    public void delete(Long id) {
        funcionRepository.deleteById(id);
    }
    
}
