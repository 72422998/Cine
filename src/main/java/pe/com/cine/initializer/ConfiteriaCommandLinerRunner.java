package pe.com.cine.initializer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pe.com.cine.entities.Confiteria;
import pe.com.cine.entities.Producto;
import pe.com.cine.entities.Sede;
import pe.com.cine.repositories.ConfiteriaRepository;
import pe.com.cine.repositories.ProductoRepository;
import pe.com.cine.repositories.SedeRepository;

@Component
public class ConfiteriaCommandLinerRunner implements CommandLineRunner{
    @Autowired
    private ConfiteriaRepository confiteriaRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private SedeRepository sedeRepository;
    @Override
    public void run(String... args) throws Exception {
        agregarConfiterias();
    }
    private void agregarConfiterias(){
        Sede sede1 = sedeRepository.findById(1L).orElse(null);

        List<Confiteria> confiterias = new ArrayList<>();

        Confiteria confiteria1 = new Confiteria("Dulce Sabor", sede1);
        confiterias.add(confiteria1);
        confiteriaRepository.saveAll(confiterias);
    }
    private void eliminarConfiterias(){confiteriaRepository.deleteAll();}
}
