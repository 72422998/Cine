package pe.com.cine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.cine.entities.Funcion;

public interface FuncionRepository extends JpaRepository<Funcion, Long>{
    
}

