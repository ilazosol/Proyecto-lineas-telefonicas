package com.telefonica.linea.app.lineasclientes.controller;

import com.telefonica.linea.app.lineasclientes.entity.ClienteEntity;
import com.telefonica.linea.app.lineasclientes.entity.OfertaEntity;
import com.telefonica.linea.app.lineasclientes.exception.ResourceNotFoundException;
import com.telefonica.linea.app.lineasclientes.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/findClientes")
    public List<ClienteEntity> getAllClients(){
        return clienteService.findClients();
    }

    @GetMapping("/findByDocument/{tipo}/{numero}")
    public ClienteEntity getClientByDocument(@PathVariable String tipo, @PathVariable String numero) throws ResourceNotFoundException {
        return clienteService.findClientByDocument(tipo,numero);
    }

    @GetMapping("/findByDatesOferta")
    public List<ClienteEntity> getClientsByDates(){
        return clienteService.findClientsByDatesOferta();
    }

    @GetMapping("/getCatalogoOfertas")
    public List<OfertaEntity> getCatalogoOfertas(){
        return clienteService.getCatalogo();
    }
}
