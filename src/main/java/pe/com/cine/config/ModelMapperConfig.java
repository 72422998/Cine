package pe.com.cine.config;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pe.com.cine.dto.CategoriaDTO;
import pe.com.cine.dto.PeliculaDTO;
import pe.com.cine.dto.ProductoDTO;
import pe.com.cine.entities.Categoria;
import pe.com.cine.entities.Pelicula;
import pe.com.cine.entities.Producto;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull())
                .setSkipNullEnabled(true);

        // Configuración para la entidad Pelicula
        modelMapper.createTypeMap(PeliculaDTO.class, Pelicula.class)
                .addMappings(mapper -> mapper.map(PeliculaDTO::getPeliculaId, Pelicula::setPeliculaId))
                .addMappings(mapper -> mapper.skip(Pelicula::setCategoria));

        // Configuración para la entidad Categoria
        modelMapper.createTypeMap(CategoriaDTO.class, Categoria.class)
                .addMappings(mapper -> mapper.map(CategoriaDTO::getCategoriaId, Categoria::setCategoriaId));

        // Configuración para la entidad Producto
        modelMapper.createTypeMap(ProductoDTO.class, Producto.class)
                .addMappings(mapper -> mapper.map(ProductoDTO::getProductoId, Producto::setProductoId));

        return modelMapper;
    }
}
