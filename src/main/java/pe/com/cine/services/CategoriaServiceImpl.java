package pe.com.cine.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cine.dto.CategoriaDTO;
import pe.com.cine.entities.Categoria;
import pe.com.cine.repositories.CategoriaRepository;
@Service
public class CategoriaServiceImpl implements CategoriaService{
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoriaDTO> findAll() {
       List<Categoria> categorias = categoriaRepository.findAll();
       return categorias.stream().map(categoria ->modelMapper.map(categoria, CategoriaDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO findById(long id) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Categoria no encontrada con ID:" + id));
        return modelMapper.map(categoria, CategoriaDTO.class);
    }

    @Override
    public CategoriaDTO add(CategoriaDTO categoriaDTO) {
       Categoria categoria = modelMapper.map(categoriaDTO, Categoria.class);
       Categoria categoriaGuardada = categoriaRepository.save(categoria);
       return modelMapper.map(categoriaGuardada, CategoriaDTO.class);
    }

    @Override
    public CategoriaDTO update(long id, CategoriaDTO categoriaDTO) {
        Categoria categoriaexistente = categoriaRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Categoria no encontrad con ID:" + id));
        modelMapper.map(categoriaDTO, categoriaexistente);
        Categoria categoriaActualizada = categoriaRepository.save(categoriaexistente);
        return modelMapper.map(categoriaActualizada, CategoriaDTO.class);
    }

    @Override
    public void delete(long id) {
        categoriaRepository.deleteById(id);
    }

    
}
