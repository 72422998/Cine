package pe.com.cine.initializer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pe.com.cine.entities.Sede;
import pe.com.cine.repositories.SedeRepository;

@Component
public class SedeCommandLinerRunner implements CommandLineRunner {
    @Autowired
    private SedeRepository sedeRepository;

    @Override
    public void run(String... args) throws Exception {
        agregarSedes();
    }
    private void agregarSedes(){
        List<Sede> sedes = new ArrayList<>();

        Sede sede1 = new Sede("Sede LimaCentro", "Jr. de la union 3405");
        sedes.add(sede1);
        sedeRepository.saveAll(sedes);
    }
    
}
