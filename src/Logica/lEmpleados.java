/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.dEmpleados;
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
public class lEmpleados {
 
    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;
    
     public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID Empleado", "Nombre del Empleado", 
            "Direccion del Empleado", "Telefono del Empleado", 
            "Edad del empleado", "Turno del empleado", 
            "Fecha de ingreso"};
        
        String[] registros = new String[7];
        totalregistros = 0;

        modelo = new DefaultTableModel(null, titulos);

        if (buscar.equals("")) {
            sSQL = "select * from empleados;";
        } else {
            sSQL = "select * from empleados where id_empleado = '" + buscar + "'";
        }

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registros[0] = rs.getString("id_empleado");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("direccion");
                registros[3] = rs.getString("telefono");
                registros[4] = rs.getString("edad");
                registros[5] = rs.getString("turno");
                registros[6] = rs.getString("fechaIngreso");

                totalregistros = totalregistros + 1;
                modelo.addRow(registros);
            }
            return modelo;

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }

    public boolean insertar(dEmpleados dts) {
        sSQL = "INSERT INTO empleados (`id_empleado`, `nombre`, `direccion`, `telefono`, `edad`, `turno`, `fechaIngreso`) "
                + "VALUES (?,?,?,?,?,?,?);";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId_empleado());
            pst.setString(2, dts.getNombre());
            pst.setString(3, dts.getDireccion());
            pst.setString(4, dts.getTelefono());
            pst.setString(5, dts.getEdad());
            pst.setString(6, dts.getTurno());
            pst.setString(7, dts.getFechaIngreso());

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

    public boolean actualizar(dEmpleados dts) {
        sSQL = "update empleados set nombre = ? , direccion = ? , telefono = ? , edad = ? , turno = ?, fechaIngreso = ?"
                + " where id_empleado = ?;";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDireccion());
            pst.setString(3, dts.getTelefono());
            pst.setString(4, dts.getEdad());
            pst.setString(5, dts.getTurno());
            pst.setString(6, dts.getFechaIngreso());
            pst.setInt(7, dts.getId_empleado());

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

    public boolean eliminar(dEmpleados dts) {
        sSQL = "Delete from empleados where id_empleado = ?;";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId_empleado());

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
