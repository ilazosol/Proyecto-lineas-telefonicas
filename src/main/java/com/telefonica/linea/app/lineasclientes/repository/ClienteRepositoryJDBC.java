package com.telefonica.linea.app.lineasclientes.repository;

import com.telefonica.linea.app.lineasclientes.entity.ClienteEntity;
import com.telefonica.linea.app.lineasclientes.entity.LineaMovilEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ClienteRepositoryJDBC {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteRepositoryJDBC.class);

    public List<ClienteEntity> listarClientesJDBC(Connection connection) throws SQLException {
        List<ClienteEntity> clientes = new ArrayList<>();
        PreparedStatement pst = null;
        List<LineaMovilEntity> lineas = new ArrayList<>();

        try {
            String sql = "";

            sql =   "select * from clientes";
            pst = connection.prepareStatement(sql);

            LOGGER.info(pst.toString());
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                ClienteEntity cliente = new ClienteEntity();
                cliente.setIdCliente(rst.getLong("id_cliente"));
                cliente.setNombre(rst.getString("nombre"));
                cliente.setApellidoPaterno(rst.getString("apellido_paterno"));
                cliente.setApellidoMaterno(rst.getString("apellido_materno"));
                cliente.setTipoDocumento(rst.getString("tipo_documento"));
                cliente.setNumeroDocumento(rst.getString("numero_documento"));
                clientes.add(cliente);
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }finally {
            connection.close();
        }
        return clientes;
    }
}
