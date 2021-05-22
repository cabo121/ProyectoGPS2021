/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author Armando
 */
public class dPedidos {

    int id_pedido;
    String nombre;
    String cliente;
    int NumClient;
    String repartidor;
    int NumRepart;
    String fecha;
    int cant_azuc;
    int cant_integ;
    int cant_fran;
    int cant_total;
    
    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getNumClient() {
        return NumClient;
    }

    public void setNumClient(int NumClient) {
        this.NumClient = NumClient;
    }

    public String getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(String repartidor) {
        this.repartidor = repartidor;
    }

    public int getNumRepart() {
        return NumRepart;
    }

    public void setNumRepart(int NumRepart) {
        this.NumRepart = NumRepart;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCant_azuc() {
        return cant_azuc;
    }

    public void setCant_azuc(int cant_azuc) {
        this.cant_azuc = cant_azuc;
    }

    public int getCant_integ() {
        return cant_integ;
    }

    public void setCant_integ(int cant_integ) {
        this.cant_integ = cant_integ;
    }

    public int getCant_fran() {
        return cant_fran;
    }

    public void setCant_fran(int cant_fran) {
        this.cant_fran = cant_fran;
    }

    public int getCant_total() {
        return cant_total;
    }

    public void setCant_total(int cant_total) {
        this.cant_total = cant_total;
    }
    
}
