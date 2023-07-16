package pe.com.cine.initializer;

import java.util.ArrayList;
import java.util.List;

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
       agregarPeliculas();
    }
    private void agregarPeliculas(){
        List<Pelicula> peliculas = new ArrayList<>();
         Categoria categoria1 = categoriaRepository.findById(1L).orElse(null);

        Pelicula pelicula1 = new Pelicula(
        "Terminator","imagen1.jpg","Un robot viene del futuro a matar a un niño pero se hace su amigo","180min","+14","James Cameron","k64P4l2Wmeg"
        );
        pelicula1.setCategoria(categoria1);
        peliculas.add(pelicula1);

        Pelicula pelicula2 = new Pelicula(
        "Prisioners","imagen4.jpg","Un padre hara lo que sea para encontrar a su hija desaparecida","180min","+14","James Cameron","KQGSWQhGMTg"
        );
        pelicula2.setCategoria(categoria1);
        peliculas.add(pelicula2);

        Pelicula pelicula3 = new Pelicula(
        "TaxiDriver","imagen9.jpg","Para sobrellevar el insomnio cronico que sufre desde su regreso de Vietnam, Travis Bickle trabaja como taxista nocturno en Nueva York.","180min","+14","James Cameron","R7xcj4Br8so"
        );
        pelicula3.setCategoria(categoria1);
        peliculas.add(pelicula3);
        
        peliculaRepository.saveAll(peliculas);
    }
    private void borrarPeliculas(){peliculaRepository.deleteAll();}
    
}
