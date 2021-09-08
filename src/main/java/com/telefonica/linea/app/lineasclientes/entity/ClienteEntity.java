package com.telefonica.linea.app.lineasclientes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clientes")
@Entity
public class ClienteEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    private String nombre;

    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Column(name="tipo_documento")
    private String tipoDocumento;

    @Column(name="numero_documento",unique = true)
    private String numeroDocumento;

    @OneToMany(mappedBy = "cliente")
    private List<LineaMovilEntity> lineas;
}
