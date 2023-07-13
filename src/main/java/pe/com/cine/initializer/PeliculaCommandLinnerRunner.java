package pe.com.cine.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pe.com.cine.entities.Categoria;
import pe.com.cine.entities.Pelicula;
import pe.com.cine.repositories.CategoriaRepository;
import pe.com.cine.repositories.PeliculaRepository;

@Component
public class PeliculaCommandLinnerRunner implements CommandLineRunner{
    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Override
    public void run(String... args) throws Exception {
        Categoria categoria1 = categoriaRepository.findById(1L).orElse(null);
        // .orElseThrow(()->new RuntimeException("categoria no encontrada"));

        Pelicula pelicula1 = new Pelicula(
            "Terminator","imagen1.jpg","Un robot viene del futuro a matar a un niño pero se hace su amigo","180min","+14","James Cameron"
        );
        pelicula1.setCategoria(categoria1);
        peliculaRepository.save(pelicula1);
        Pelicula pelicula2 = new Pelicula(
            "El Aro","imagen2.jpg","Una chica con cabello largo busca un corte","180min","+18","James Wan"
        );
        pelicula2.setCategoria(categoria1);
        peliculaRepository.save(pelicula2);

        Pelicula pelicula3 = new Pelicula(
            "Prisioners","imagen4.jpg","Un padre hara lo que sea para encontrar a su hija","220min","+14","Vince Gillegan"
        );
        pelicula3.setCategoria(categoria1);
        peliculaRepository.save(pelicula3);
        // Pelicula pelicula2= new Pelicula(
        //     "Transformers:El despertar de las bestias","imagen6.jpg","Los transformers viajan a Peru","Accion","255min","+10","James Gunn",null
        // );
        // peliculaRepository.save(pelicula2);
    }

    
}
