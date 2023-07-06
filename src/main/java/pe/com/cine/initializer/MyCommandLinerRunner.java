package pe.com.cine.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pe.com.cine.entities.Categoria;
import pe.com.cine.entities.Pelicula;
import pe.com.cine.repositories.CategoriaRepository;
import pe.com.cine.repositories.PeliculaRepository;

@Component
public class MyCommandLinerRunner implements CommandLineRunner{
    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Override
    public void run(String... args) throws Exception {
        Categoria categoria1 = categoriaRepository.findById(1L).orElse(null);
        // .orElseThrow(()->new RuntimeException("categoria no encontrada"));

        Pelicula pelicula1 = new Pelicula(
            "Terminator","imagen1.webp","Un robot viene del futuro a matar a un niño pero se hace su amigo","Accion","180min","+14","James Cameron"
        );
        pelicula1.setCategoria(categoria1);
        peliculaRepository.save(pelicula1);
        // Pelicula pelicula2= new Pelicula(
        //     "Transformers:El despertar de las bestias","imagen6.jpg","Los transformers viajan a Peru","Accion","255min","+10","James Gunn",null
        // );
        // peliculaRepository.save(pelicula2);
    }

    
}
