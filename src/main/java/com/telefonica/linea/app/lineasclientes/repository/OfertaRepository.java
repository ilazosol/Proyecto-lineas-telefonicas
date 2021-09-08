package com.telefonica.linea.app.lineasclientes.repository;

import com.telefonica.linea.app.lineasclientes.entity.OfertaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaRepository extends JpaRepository<OfertaEntity,Long> {
}
