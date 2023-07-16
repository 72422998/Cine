package pe.com.cine.initializer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pe.com.cine.entities.Funcion;
import pe.com.cine.entities.Pelicula;
import pe.com.cine.entities.Sala;
import pe.com.cine.repositories.FuncionRepository;
import pe.com.cine.repositories.PeliculaRepository;
import pe.com.cine.repositories.SalaRepository;

@Component
public class FuncionCommandLinerRunner implements CommandLineRunner{
    @Autowired
    private FuncionRepository funcionRepository;
    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private SalaRepository salaRepository;

    @Override
    public void run(String... args) throws Exception {
        agregarFunciones();
}
    private void agregarFunciones(){
        Pelicula pelicula1 = peliculaRepository.findById(1L).orElse(null);
        Sala sala1 = salaRepository.findById(1L).orElse(null);

        List<Funcion> funciones = new ArrayList<>();

        Funcion funcion1 = new Funcion("2:00", "3:45", pelicula1, sala1);
        funciones.add(funcion1);

        funcionRepository.saveAll(funciones);
    }
    private void eliminarFunciones(){funcionRepository.deleteAll();}
}
