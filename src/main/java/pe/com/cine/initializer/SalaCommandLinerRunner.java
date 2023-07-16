package pe.com.cine.initializer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pe.com.cine.entities.Sala;
import pe.com.cine.entities.Sede;
import pe.com.cine.repositories.SalaRepository;
import pe.com.cine.repositories.SedeRepository;

@Component
public class SalaCommandLinerRunner  implements CommandLineRunner{
@Autowired
private SalaRepository salaRepository;
@Autowired
private SedeRepository sedeRepository;

    @Override
    public void run(String... args) throws Exception {
        agregarSalas();
        
    }
    private void agregarSalas(){
        Sede sede1 = sedeRepository.findById(1L).orElse(null);

        List<Sala> salas = new ArrayList<>();

        Sala sala1 = new Sala("sala1", "Segundo Piso", "250 Personas", "Tarde", sede1); 
        salas.add(sala1);

        salaRepository.saveAll(salas);
    }
    private void eliminarSalas(){salaRepository.deleteAll();}
}
    

