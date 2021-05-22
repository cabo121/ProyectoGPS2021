/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.dClientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Armando
 */
public class lClientes {
    
    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;
    
     public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID Cliente", "Nombre del cliente", 
            "Direccion del Cliente", "Telefono del cliente", 
            "Pedido"};
        
        String[] registros = new String[5];
        totalregistros = 0;

        modelo = new DefaultTableModel(null, titulos);

        if (buscar.equals("")) {
            sSQL = "select * from clientes;";
        } else {
            sSQL = "select * from clientes where NumClient = '" + buscar + "'";
        }

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registros[0] = rs.getString("NumClient");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("direccion");
                registros[3] = rs.getString("telefono");
                registros[4] = rs.getString("pedido");

                totalregistros = totalregistros + 1;
                modelo.addRow(registros);
            }
            return modelo;

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }

    public boolean insertar(dClientes dts) {
        sSQL = "INSERT INTO clientes (`NumClient`, `nombre`, `direccion`, "
                + "`telefono`, `pedido`) "
                + "VALUES (?,?,?,?,?);";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getNumClient());
            pst.setString(2, dts.getNombre());
            pst.setString(3, dts.getDireccion());
            pst.setString(4, dts.getTelefono());
            pst.setInt(5, dts.getPedido());

            int n = pst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean actualizar(dClientes dts) {
        sSQL = "update clientes set nombre = ? , direccion = ? , telefono = ? , "
                + "pedido = ? "
                + " where NumClient = ?;";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDireccion());
            pst.setString(3, dts.getTelefono());
            pst.setInt(4, dts.getPedido());
            pst.setInt(5, dts.getNumClient());

            int n = pst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean eliminar(dClientes dts) {
        sSQL = "Delete from clientes where NumClient = ?;";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getNumClient());

            int n = pst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
}
