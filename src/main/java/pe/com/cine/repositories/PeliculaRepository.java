package pe.com.cine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.cine.entities.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long>{
}
