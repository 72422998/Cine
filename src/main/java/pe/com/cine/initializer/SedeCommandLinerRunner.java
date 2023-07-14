package pe.com.cine.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pe.com.cine.entities.Sede;
import pe.com.cine.repositories.SedeRepository;

@Component
public class SedeCommandLinerRunner implements CommandLineRunner {
    @Autowired
    private SedeRepository
    sedeRepository;

    @Override
    public void run(String... args) throws Exception {
        /* Sede sede1=new Sede("Norte","Av.lima");
        sedeRepository.save(sede1);

        Sede sede2=new Sede("Sur","Av.Cultura");
        sedeRepository.save(sede2); */
    }
    
}
