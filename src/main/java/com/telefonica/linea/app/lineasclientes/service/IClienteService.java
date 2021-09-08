package com.telefonica.linea.app.lineasclientes.service;

import com.telefonica.linea.app.lineasclientes.entity.ClienteEntity;
import com.telefonica.linea.app.lineasclientes.entity.OfertaEntity;
import com.telefonica.linea.app.lineasclientes.exception.ResourceNotFoundException;

import java.util.List;

public interface IClienteService {

    List<ClienteEntity> findClients();

    ClienteEntity findClientByDocument(String tipo, String numero) throws ResourceNotFoundException;

    List<ClienteEntity> findClientsByDatesOferta();

    List<OfertaEntity> getCatalogo();
}
