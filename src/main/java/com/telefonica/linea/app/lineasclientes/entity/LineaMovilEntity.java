package com.telefonica.linea.app.lineasclientes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lineas_moviles")
@Entity
public class LineaMovilEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_linea_movil")
    private Long idLineaMovil;

    @Column(name = "numero_telefono",unique = true)
    private Integer numeroTelefono;

    @Column(name = "estado")
    private String estado;

    @Column(name="tipo_linea")
    private String tipoLinea;

    @Column(name="nombre_plan")
    private String nombrePlan;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "lineas_ofertas",
            joinColumns = @JoinColumn(name = "linea_movil_id"),
            inverseJoinColumns = @JoinColumn(name = "oferta_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = { "linea_movil_id", "oferta_id" })}
    )
    private List<OfertaEntity> ofertas;
}
