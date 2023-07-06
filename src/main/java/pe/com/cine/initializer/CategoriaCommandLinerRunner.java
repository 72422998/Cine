package pe.com.cine.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pe.com.cine.entities.Categoria;
import pe.com.cine.repositories.CategoriaRepository;
@Component
public class CategoriaCommandLinerRunner implements CommandLineRunner{
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public void run(String... args) throws Exception {
       Categoria categoria1 = new Categoria(
            "Accion"
       );
       categoriaRepository.save(categoria1);
       Categoria categoria2 = new Categoria(
        "Suspenso"
       );
       categoriaRepository.save(categoria2);
    }
    
}
