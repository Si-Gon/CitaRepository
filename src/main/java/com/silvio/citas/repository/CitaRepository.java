package com.silvio.citas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.silvio.citas.model.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita,Long> {

}
