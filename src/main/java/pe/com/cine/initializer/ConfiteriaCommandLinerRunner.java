package pe.com.cine.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
    //     Producto producto1 = productoRepository.findById(1L).orElse(null);

    //     Sede sede1 = sedeRepository.findById(1L).orElse(null);

    //     Confiteria confiteria1 = new Confiteria("Dulce Placer");
    }
}
