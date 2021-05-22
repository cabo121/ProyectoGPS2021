/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.dPedidos;
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
public class lPedidos {
    
    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;
    
     public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID Pedido", "Nombre del pedido", 
            "ID del Cliente", "Nombre del cliente", 
            "ID del Repartidor", "Nombre del Repartidor", 
            "Fecha del pedido","Cantidad de Azucar","Cantidad de Integral",
            "Cantidad de Frances","Cantidad total"};
        
        String[] registros = new String[11];
        totalregistros = 0;

        modelo = new DefaultTableModel(null, titulos);

        if (buscar.equals("")) {
            sSQL = "select * from pedidos;";
        } else {
            sSQL = "select * from pedidos where id_pedido = '" + buscar + "'";
        }

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registros[0] = rs.getString("id_pedido");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("NumClient");
                registros[3] = rs.getString("cliente");
                registros[4] = rs.getString("NumRepart");
                registros[5] = rs.getString("repartidor");
                registros[6] = rs.getString("fecha");
                registros[6] = rs.getString("cant_azuc");
                registros[6] = rs.getString("cant_int");
                registros[6] = rs.getString("cant_fran");
                registros[6] = rs.getString("cant_total");

                totalregistros = totalregistros + 1;
                modelo.addRow(registros);
            }
            return modelo;

        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }

    public boolean insertar(dPedidos dts) {
        sSQL = "INSERT INTO pedidos (`id_pedido`, `nombre`, `NumClient`, "
                + "`cliente`, `NumRepart`, `repartidor`, `fecha` "
                + ", `cant_azuc` , `cant_int` , `cant_fran` , `cant_total`) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId_pedido());
            pst.setString(2, dts.getNombre());
            pst.setInt(3, dts.getNumClient());
            pst.setString(4, dts.getCliente());
            pst.setInt(5, dts.getNumRepart());
            pst.setString(6, dts.getRepartidor());
            pst.setString(7, dts.getFecha());
            pst.setInt(8, dts.getCant_azuc());
            pst.setInt(9, dts.getCant_integ());
            pst.setInt(10, dts.getCant_fran());
            pst.setInt(11, dts.getCant_total());

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

    public boolean actualizar(dPedidos dts) {
        sSQL = "update pedidos set nombre = ? , NumClient = ? , "
                + "cliente = ? , NumRepart = ? , repartidor = ?, "
                + "fecha = ? , cant_azuc = ? , cant_int = ? "
                + ", cant_fran = ? , cant_total = ? "
                + " where id_pedido = ?;";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setInt(2, dts.getNumClient());
            pst.setString(3, dts.getCliente());
            pst.setInt(4, dts.getNumRepart());
            pst.setString(5, dts.getRepartidor());
            pst.setString(6, dts.getFecha());
            pst.setInt(7, dts.getCant_azuc());
            pst.setInt(8, dts.getCant_integ());
            pst.setInt(9, dts.getCant_fran());
            pst.setInt(10, dts.getCant_total());
            pst.setInt(11, dts.getId_pedido());

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

    public boolean eliminar(dPedidos dts) {
        sSQL = "Delete from pedidos where id_pedido = ?;";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getId_pedido());

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
