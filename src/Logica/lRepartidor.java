/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.dEmpleados;
import Datos.dRepartidores;
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
public class lRepartidor {
    
    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;
    
     public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID Repartidor", "Nombre del Repartidor", 
            "Telefono del Empleado", "Direccion del Empleado", 
            "Edad del empleado", "Tipo de Ruta", 
            "ID del Pedido"};
        
        String[] registros = new String[7];
        totalregistros = 0;

        modelo = new DefaultTableModel(null, titulos);

        if (buscar.equals("")) {
            sSQL = "select * from repartidor;";
        } else {
            sSQL = "select * from repartidor where id_repart = '" + buscar + "'";
        }

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registros[0] = rs.getString("id_repart");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("telefono");
                registros[3] = rs.getString("direccion");
                registros[4] = rs.getString("edad");
                registros[5] = rs.getString("tipo");
                registros[6] = rs.getString("pedido");

                totalregistros = totalregistros + 1;
                modelo.addRow(registros);
            }
            return modelo;

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }

    public boolean insertar(dRepartidores dts) {
        sSQL = "INSERT INTO repartidor (`id_repart`, `nombre`, `telefono`, "
                + "`direccion`, `edad`, `tipo`, `pedido`) "
                + "VALUES (?,?,?,?,?,?,?);";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId_repart());
            pst.setString(2, dts.getNombre());
            pst.setString(3, dts.getTelefono());
            pst.setString(4, dts.getDireccion());
            pst.setString(5, dts.getEdad());
            pst.setInt(6, dts.getTipo());
            pst.setInt(7, dts.getPedido());

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

    public boolean actualizar(dRepartidores dts) {
        sSQL = "update repartidor set nombre = ? , telefono = ? , direccion= ? , "
                + "edad = ? , tipo = ?, pedido = ?"
                + " where id_repart = ?;";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getTelefono());
            pst.setString(3, dts.getDireccion());
            pst.setString(4, dts.getEdad());
            pst.setInt(5, dts.getTipo());
            pst.setInt(6, dts.getPedido());
            pst.setInt(7, dts.getId_repart());

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

    public boolean eliminar(dRepartidores dts) {
        sSQL = "Delete from repartidor where id_repart = ?;";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId_repart());

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
