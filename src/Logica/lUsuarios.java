/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.dClientes;
import Datos.dUsuarios;
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
public class lUsuarios {
    
    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;
    
     public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID Usuario", "Nombre del usuario", 
            "Contrasena", "Fecha de ingreso"};
        
        String[] registros = new String[4];
        totalregistros = 0;

        modelo = new DefaultTableModel(null, titulos);

        if (buscar.equals("")) {
            sSQL = "select * from usuarios;";
        } else {
            sSQL = "select * from usuarios where id_usuarios = '" + buscar + "'";
        }

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registros[0] = rs.getString("id_usuarios");
                registros[1] = rs.getString("usuario");
                registros[2] = rs.getString("contra");
                registros[3] = rs.getString("fechaIngreso");

                totalregistros = totalregistros + 1;
                modelo.addRow(registros);
            }
            return modelo;

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }

    public boolean insertar(dUsuarios dts) {
        sSQL = "INSERT INTO usuarios (`id_usuarios`, `usuario`, `contra`, "
                + "`fechaIngreso`) "
                + "VALUES (?,?,?,?);";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId_usuarios());
            pst.setString(2, dts.getUsuario());
            pst.setString(3, dts.getContra());
            pst.setString(4, dts.getFechaIngreso());

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

    public boolean actualizar(dUsuarios dts) {
        sSQL = "update usuarios set usuario = ? , contra = ? , fechaIngreso = ? "
                + " where id_usuarios = ?;";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getUsuario());
            pst.setString(2, dts.getContra());
            pst.setString(3, dts.getFechaIngreso());
            pst.setInt(4, dts.getId_usuarios());

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

    public boolean eliminar(dUsuarios dts) {
        sSQL = "Delete from usuarios where id_usuarios = ?;";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId_usuarios());

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
