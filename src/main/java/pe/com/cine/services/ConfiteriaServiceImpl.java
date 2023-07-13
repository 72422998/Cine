package pe.com.cine.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.cine.dto.ConfiteriaDTO;
import pe.com.cine.entities.Confiteria;
import pe.com.cine.entities.Producto;
import pe.com.cine.entities.Sede;
import pe.com.cine.repositories.ConfiteriaRepository;
import pe.com.cine.repositories.ProductoRepository;
import pe.com.cine.repositories.SedeRepository;

@Service
public class ConfiteriaServiceImpl implements ConfiteriaService {
    @Autowired
    private ConfiteriaRepository confiteriaRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private SedeRepository sedeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ConfiteriaDTO> findAll() {
        List<Confiteria> confiterias = confiteriaRepository.findAll();
        return confiterias.stream().map(confiteria -> modelMapper.map(confiteria, ConfiteriaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ConfiteriaDTO findById(Long id) {
        Confiteria confiteria = confiteriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Confiteria no encontrada con ID: " + id));
        return modelMapper.map(confiteria, ConfiteriaDTO.class);
    }

    @Override
    public ConfiteriaDTO add(ConfiteriaDTO confiteriaDTO) {
        Confiteria confiteria = modelMapper.map(confiteriaDTO, Confiteria.class);
        // Obtener el producto por su ID y establecerlo en la confiteria
        Producto producto = productoRepository.findById(confiteriaDTO.getProducto().getProductoId()).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con su ID: " + confiteriaDTO.getProducto().getProductoId() ));
        // confiteria.setProductos(producto);
        // Obtener la sede por su ID y establecerlo en la confiteria
        Sede sede = sedeRepository.findById(confiteriaDTO.getSede().getSedeId()).orElseThrow(() -> new IllegalArgumentException("Sede no encontrada con su Id: " + confiteriaDTO.getSede().getSedeId()));
        confiteria.setSede(sede);

        Confiteria confiteriaGuardada = confiteriaRepository.save(confiteria);
        return modelMapper.map(confiteriaGuardada, ConfiteriaDTO.class);

    }

    @Override
    public ConfiteriaDTO update(Long id, ConfiteriaDTO confiteriaDTO) {
        Confiteria confiteriaExistente = confiteriaRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Confiteria no encontrada con ID: " + id));

        // Producto productoActual = confiteriaExistente.getProductos();

        Sede sedeActual = confiteriaExistente.getSede();

        confiteriaDTO.getSede().setSedeId(sedeActual.getSedeId());

        modelMapper.map(confiteriaDTO, confiteriaExistente);

        Confiteria confiteriaActualizada = confiteriaRepository.save(confiteriaExistente);

        return modelMapper.map(confiteriaActualizada, ConfiteriaDTO.class);

    }

    @Override
    public void delete(Long id) {
        confiteriaRepository.deleteById(id);
    }


}
