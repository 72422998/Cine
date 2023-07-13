package pe.com.cine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.cine.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
