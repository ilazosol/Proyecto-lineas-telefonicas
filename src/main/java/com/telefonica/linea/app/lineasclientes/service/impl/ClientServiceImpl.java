package com.telefonica.linea.app.lineasclientes.service.impl;

import com.telefonica.linea.app.lineasclientes.entity.ClienteEntity;
import com.telefonica.linea.app.lineasclientes.entity.LineaMovilEntity;
import com.telefonica.linea.app.lineasclientes.entity.OfertaEntity;
import com.telefonica.linea.app.lineasclientes.repository.ClienteRepository;
import com.telefonica.linea.app.lineasclientes.repository.OfertaRepository;
import com.telefonica.linea.app.lineasclientes.service.IClienteService;
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

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    //Se listan todos los clientes con los filtros necesarios en el query de tipo y numero
    @Override
    public List<ClienteEntity> findClientsByDocument(String tipo, String numero) {
        return clienteRepository.findClientByDocument(tipo,numero);
    }

    //Metodo en el que obtengo todos los clientes desde la bd y mediante programacion funcional devuelvo la logica necesaria
    //obteniendo todos los clientes que tengan por lo menos 3 líneas móviles activas con al menos 1 oferta cada una,
    //mostrandose solo la mas proxima a finalizar
    @Override
    public List<ClienteEntity> findClientsByDatesOferta(String fechaInicio, String fechaFin) {
        List<ClienteEntity> clientes = clienteRepository.findAll()
                .stream()
                .filter( c -> {
                    Integer cant = c.getLineas()
                            .stream()
                            .filter( l -> l.getEstado().equals("Activo") && l.getOfertas().size() > 0)
                            .collect(Collectors.toList())
                            .size();
                    return cant >= 3;
                })
                .map( c -> {
                       List<LineaMovilEntity> lineas = c.getLineas().stream().map(l -> {
                           Collections.sort(l.getOfertas(), new Comparator<OfertaEntity>() {
                               @Override
                               public int compare(OfertaEntity o1, OfertaEntity o2) {
                                   return o2.getFechaFin().compareTo(o1.getFechaFin());
                               }
                           });
                           OfertaEntity oferta = l.getOfertas().stream().filter( of -> {
                               Date date = new Date();
                               Integer value = of.getFechaFin().compareTo(new Timestamp(date.getTime()));
                               return value == -1;
                           }).findFirst().get();
                           l.setOfertas(Arrays.asList(oferta));
                           return l;
                       }).collect(Collectors.toList());
                       c.setLineas(lineas);
                       return c;
                })
                .collect(Collectors.toList());

        return clientes;
    }

    @Override
    @Cacheable("ofertaCache")
    public List<OfertaEntity> getCatalogo() {
        System.out.println("Entro a obtener las ofertas por cache");
        return ofertaRepository.findAll();
    }

}
