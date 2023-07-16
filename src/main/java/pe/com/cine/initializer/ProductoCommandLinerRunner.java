package pe.com.cine.initializer;

import java.util.ArrayList;
import java.util.List;

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
        agregarProductos();
    }
    private void agregarProductos(){
        List<Producto> productos = new ArrayList<>();

        Producto producto1 = new Producto("PopCorn", "producto1.jpg", 10.90); 
        productos.add(producto1);

        Producto producto2 = new Producto("Nachos", "producto2.jpg", 12.90); 
        productos.add(producto2);

        Producto producto3 = new Producto("Sublime", "producto3.jpg", 6.90); 
        productos.add(producto3);

        Producto producto4 = new Producto("CocaCola", "producto4.jpg", 15.90); 
        productos.add(producto4);

        Producto producto5 = new Producto("Pepsi", "producto5.jpg", 14.90); 
        productos.add(producto5);

        Producto producto6 = new Producto("HotDog", "producto6.jpg", 18.90); 
        productos.add(producto6);

        productoRepository.saveAll(productos);
    }
    private void eliminarProductos(){productoRepository.deleteAll();}
}
