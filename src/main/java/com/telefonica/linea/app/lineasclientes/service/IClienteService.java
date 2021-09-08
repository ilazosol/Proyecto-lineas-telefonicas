package com.telefonica.linea.app.lineasclientes.service;

import com.telefonica.linea.app.lineasclientes.entity.ClienteEntity;
import com.telefonica.linea.app.lineasclientes.entity.OfertaEntity;

import java.util.List;

public interface IClienteService {

    List<ClienteEntity> findClientsByDocument(String tipo, String numero);

    List<ClienteEntity> findClientsByDatesOferta(String fechaInicio, String fechaFin);

    List<OfertaEntity> getCatalogo();
}
