/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Armando
 */
public class conexion {

    // Declaramos la conexion a mysql
    private static Connection con;
    // Declaramos los datos de conexion a la bd
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String user="root";
    private static final String pass="";
    private static final String url="jdbc:mysql://localhost:3306/panadb";
    
    public Connection conectar() {
        Connection link = null;

        try {
            Class.forName(driver);
            link = DriverManager.getConnection(this.url, this.user, this.pass);
            System.out.println("Conexion establecida");
        } catch (Exception ex) {
            System.out.println("Error de conexion" + ex);
        }

        return link;
    }
}
