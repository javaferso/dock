/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smu.vision;
import java.util.Date;
/**
 *
 * @author JFerreira
 */
public class BalanzaNagios {

    private int id;
    private int tiendaNumero;
    private String direccionIp;
    private int habilitado;
    private Date fechaActualizacion;
    private String hostName;
    private String nombre;
    private int puerto;
    private String estadoPing;
    private String fechaActualizacionNagios;
    private String marca;

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTiendaNumero() {
        return tiendaNumero;
    }

    public void setTiendaNumero(int tiendaNumero) {
        this.tiendaNumero = tiendaNumero;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public String getEstadoPing() {
        return estadoPing;
    }

    public void setEstadoPing(String estadoPing) {
        this.estadoPing = estadoPing;
    }

    public String getFechaActualizacionNagios() {
        return fechaActualizacionNagios;
    }

    public void setFechaActualizacionNagios(String fechaActualizacionNagios) {
        this.fechaActualizacionNagios = fechaActualizacionNagios;
    }

    public void setMarca(String na) {
        this.marca = na;
    }
}

    
