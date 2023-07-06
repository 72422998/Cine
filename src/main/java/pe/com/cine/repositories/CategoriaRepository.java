package pe.com.cine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.cine.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long>{
    
}
