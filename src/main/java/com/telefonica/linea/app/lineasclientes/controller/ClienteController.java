package com.telefonica.linea.app.lineasclientes.controller;

import com.telefonica.linea.app.lineasclientes.entity.ClienteEntity;
import com.telefonica.linea.app.lineasclientes.entity.OfertaEntity;
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

    @GetMapping("/findByDocument/{tipo}/{numero}")
    public List<ClienteEntity> getClientsByDocument(@PathVariable String tipo, @PathVariable String numero){
        return clienteService.findClientsByDocument(tipo,numero);
    }

    @GetMapping("/findByDatesOferta/{fechaInicio}/{fechaFin}")
    public List<ClienteEntity> getClientsByDates(@PathVariable String fechaInicio, @PathVariable String fechaFin){
        return clienteService.findClientsByDatesOferta(fechaInicio,fechaFin);
    }

    @GetMapping("/getCatalogoOfertas")
    public List<OfertaEntity> getCatalogoOfertas(){
        return clienteService.getCatalogo();
    }
}
