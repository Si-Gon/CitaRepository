package com.silvio.citas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.silvio.citas.model.Cita;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita,Long> {
    
    // SELECT * FROM citas WHERE activa = true
    List<Cita> findByActivaTrue();

}
