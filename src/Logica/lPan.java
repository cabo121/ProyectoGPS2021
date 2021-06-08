/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.dClientes;
import Datos.dPan;
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
public class lPan {
    
    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;
    
     public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID Pan", "Nombre del Pan", 
            "Clase del Pan", "Cantidad del Pan"};
        
        String[] registros = new String[4];
        totalregistros = 0;

        modelo = new DefaultTableModel(null, titulos);

        if (buscar.equals("")) {
            sSQL = "select * from pan;";
        } else {
            sSQL = "select * from pan where id_pan = '" + buscar + "'";
        }

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registros[0] = rs.getString("id_pan");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("clase");
                registros[3] = rs.getString("cantidad");

                totalregistros = totalregistros + 1;
                modelo.addRow(registros);
            }
            return modelo;

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }

    public boolean insertar(dPan dts) {
        sSQL = "INSERT INTO pan (`id_pan`, `nombre`, `clase`, "
                + "`cantidad`) "
                + "VALUES (?,?,?,?);";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId_pan());
            pst.setString(2, dts.getNombre());
            pst.setString(3, dts.getClase());
            pst.setInt(4, dts.getCantidad());

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

    public boolean actualizar(dPan dts) {
        sSQL = "update pan set nombre = ? , clase = ? , cantidad = ? "
                + " where id_pan = ?;";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getClase());
            pst.setInt(3, dts.getCantidad());
            pst.setInt(4, dts.getId_pan());

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

    public boolean eliminar(dPan dts) {
        sSQL = "Delete from pan where id_pan = ?;";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId_pan());

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
