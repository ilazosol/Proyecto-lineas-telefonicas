package com.telefonica.linea.app.lineasclientes.repository;

import com.telefonica.linea.app.lineasclientes.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity,Long> {

    @Query("select c from ClienteEntity c " +
            "where c.tipoDocumento = ?1 and c.numeroDocumento = ?2")
    ClienteEntity findClientByDocument(String tipo, String numero);
}
