package pe.com.cine.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pe.com.cine.entities.Producto;
import pe.com.cine.repositories.ProductoRepository;

@Component
public class ProductoCommandLinerRunner implements CommandLineRunner {
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void run(String... args) throws Exception {
        /* Producto producto1 = new Producto(
                "Chocolate",
                2.5);
        productoRepository.save(producto1);
        Producto producto2 = new Producto(
                "Nachos",
                15.80);
        productoRepository.save(producto2); */
    }
}
