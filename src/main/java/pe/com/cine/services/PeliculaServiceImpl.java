package pe.com.cine.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pe.com.cine.dto.PeliculaDTO;
import pe.com.cine.entities.Categoria;
import pe.com.cine.entities.Pelicula;
import pe.com.cine.repositories.CategoriaRepository;
import pe.com.cine.repositories.PeliculaRepository;
@Service
public class PeliculaServiceImpl implements PeliculaService{
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<PeliculaDTO> findAll(Pageable pageable) {
        Page<Pelicula> peliculas  = peliculaRepository.findAll(pageable);
        return peliculas.map(pelicula -> modelMapper.map(pelicula, PeliculaDTO.class));
    }

    @Override
    public PeliculaDTO findById(Long id) {
        Pelicula pelicula = peliculaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pelicula no encontrada cond ID: " + id));

        return modelMapper.map(pelicula, PeliculaDTO.class);
    }

    @Override
    public PeliculaDTO add(PeliculaDTO peliculaDTO) {
        Pelicula pelicula = modelMapper.map(peliculaDTO, Pelicula.class);

        // Obtener la categoria por su ID y establecerla en la pelicula
        Categoria categoria = categoriaRepository.findById(peliculaDTO.getCategoria().getCategoriaId()).orElseThrow(() -> new IllegalArgumentException("Categoria no encontrada cond ID: " + peliculaDTO.getCategoria().getCategoriaId()));
        pelicula.setCategoria(categoria);

        Pelicula peliculaGuardada = peliculaRepository.save(pelicula);
        return modelMapper.map(peliculaGuardada, PeliculaDTO.class);
    }

    @Override
    public PeliculaDTO update(Long id, PeliculaDTO peliculaDTO) {
        Pelicula peliculaExistente = peliculaRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Pelicula no encontrada con ID: " + id));

    // Obtener la categoría actual de la película
    Categoria categoriaActual = peliculaExistente.getCategoria();

    // Establecer el identificador de la categoría actual en el objeto peliculaDTO
    peliculaDTO.getCategoria().setCategoriaId(categoriaActual.getCategoriaId());

    // Realizar el mapeo de los demás atributos de peliculaDTO a peliculaExistente
    modelMapper.map(peliculaDTO, peliculaExistente);

    // Guardar la película actualizada en la base de datos
    Pelicula peliculaActualizada = peliculaRepository.save(peliculaExistente);

    return modelMapper.map(peliculaActualizada, PeliculaDTO.class);
    }

    @Override
    public void delete(Long id) {
        peliculaRepository.deleteById(id);
    }
    
    
}
