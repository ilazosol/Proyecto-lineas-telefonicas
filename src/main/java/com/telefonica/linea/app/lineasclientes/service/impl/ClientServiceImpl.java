package com.telefonica.linea.app.lineasclientes.service.impl;

import com.telefonica.linea.app.lineasclientes.entity.ClienteEntity;
import com.telefonica.linea.app.lineasclientes.entity.LineaMovilEntity;
import com.telefonica.linea.app.lineasclientes.entity.OfertaEntity;
import com.telefonica.linea.app.lineasclientes.exception.ResourceNotFoundException;
import com.telefonica.linea.app.lineasclientes.repository.ClienteRepository;
import com.telefonica.linea.app.lineasclientes.repository.OfertaRepository;
import com.telefonica.linea.app.lineasclientes.service.IClienteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements IClienteService {

    Logger log = LogManager.getLogger(ClientServiceImpl.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    @Override
    public List<ClienteEntity> findClients() {
        return clienteRepository.findAll();
    }

    //Se listan todos los clientes con los filtros necesarios en el query de tipo y numero
    @Override
    public ClienteEntity findClientByDocument(String tipo, String numero) throws ResourceNotFoundException {
        ClienteEntity cliente= clienteRepository.findClientByDocument(tipo,numero);
        if(cliente != null){
            return clienteRepository.findClientByDocument(tipo,numero);
        }else{
            throw new ResourceNotFoundException("No se encontro el cliente con el documento indicado");
        }
    }

    /*Metodo en el que obtengo todos los clientes desde la bd y mediante programacion funcional devuelvo la logica necesaria
    obteniendo todos los clientes que tengan por lo menos 3 líneas móviles activas con al menos 1 oferta cada una,
    mostrandose solo la mas proxima a finalizar*/
    @Override
    public List<ClienteEntity> findClientsByDatesOferta() {
        List<ClienteEntity> clientes = clienteRepository.findAll()
                .stream()
                .filter( c -> {
                    Integer cant = c.getLineas()
                            .stream()
                            .filter( l -> l.getEstado().equals("Activo") && l.getOfertas().size() > 0)
                            .collect(Collectors.toList())
                            .size();
                    log.info("Cantidad de lineas activas del cliente y con al menos una oferta: "+c.getNombre()+ " con "+c.getTipoDocumento()+" numero: "+c.getNumeroDocumento()+" :"+cant);
                    return cant >= 3;
                })
                .map( c -> {
                       List<LineaMovilEntity> lineas = c.getLineas().stream().map(l -> {
                           Collections.sort(l.getOfertas(), new Comparator<OfertaEntity>() {
                               @Override
                               public int compare(OfertaEntity o1, OfertaEntity o2) {
                                   return o1.getFechaFin().compareTo(o2.getFechaFin());
                               }
                           });
                           OfertaEntity ofertaProxAcabar = l.getOfertas().stream().filter( of -> {
                               Date date = new Date();
                               Integer value = of.getFechaFin().compareTo(new Timestamp(date.getTime()));
                               return value == 1;
                           }).findFirst().orElse(new OfertaEntity());

                           l.setOfertas(Arrays.asList(ofertaProxAcabar));
                           return l;
                       }).collect(Collectors.toList());
                       c.setLineas(lineas);
                       return c;
                })
                .collect(Collectors.toList());
        log.info("Cantidad de clientes con al menos 3 lineas moviles activas y una oferta cada una: "+clientes.size());
        return clientes;
    }

    @Override
    @Cacheable("ofertaCache")
    public List<OfertaEntity> getCatalogo() {
        log.info("Entro a obtener las ofertas por cache, se van a guardar aqui por 30 segundos");
        return ofertaRepository.findAll();
    }

}
