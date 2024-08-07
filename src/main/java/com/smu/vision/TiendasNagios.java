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
public class TiendasNagios {
    
    private Integer id;    
    private String ipAddress;
  
    private String ipEnlace;
    private String direccion;
    private String ciudad;
    private String ipVirtual;
    private String estadoIp;
    private String estadoEnlace;
    private String formatoCodigo;
    private String localTxt;
    private String hostname;
    private String hostTipo;
    private Boolean flejeElectronico;
    private Date updatedAt;
    private Date createdAt;
    private Date modified;
    private String nombreTienda;
    private String formato;
    private String local;
    
    
    public TiendasNagios() {
        
    }   

    public TiendasNagios(Integer id, String ipAddress, String ipEnlace, String direccion, String ciudad, String ipVirtual, String estadoIp, String estadoEnlace, String formatoCodigo, String localTxt, String hostname, String hostTipo, Boolean flejeElectronico, Date updatedAt, Date createdAt, Date modified, String nombreTienda, String formato, String local) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.ipEnlace = ipEnlace;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.ipVirtual = ipVirtual;
        this.estadoIp = estadoIp;
        this.estadoEnlace = estadoEnlace;
        this.formatoCodigo = formatoCodigo;
        this.localTxt = localTxt;
        this.hostname = hostname;
        this.hostTipo = hostTipo;
        this.flejeElectronico = flejeElectronico;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.modified = modified;
        this.nombreTienda = nombreTienda;
        this.formato = formato;
        this.local = local;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpEnlace() {
        return ipEnlace;
    }

    public void setIpEnlace(String ipEnlace) {
        this.ipEnlace = ipEnlace;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getIpVirtual() {
        return ipVirtual;
    }

    public void setIpVirtual(String ipVirtual) {
        this.ipVirtual = ipVirtual;
    }

    public String getEstadoIp() {
        return estadoIp;
    }

    public void setEstadoIp(String estadoIp) {
        this.estadoIp = estadoIp;
    }

    public String getEstadoEnlace() {
        return estadoEnlace;
    }

    public void setEstadoEnlace(String estadoEnlace) {
        this.estadoEnlace = estadoEnlace;
    }

    public String getFormatoCodigo() {
        return formatoCodigo;
    }

    public void setFormatoCodigo(String formatoCodigo) {
        this.formatoCodigo = formatoCodigo;
    }

    public String getLocalTxt() {
        return localTxt;
    }

    public void setLocalTxt(String localTxt) {
        this.localTxt = localTxt;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getHostTipo() {
        return hostTipo;
    }

    public void setHostTipo(String hostTipo) {
        this.hostTipo = hostTipo;
    }

    public Boolean getFlejeElectronico() {
        return flejeElectronico;
    }

    public void setFlejeElectronico(Boolean flejeElectronico) {
        this.flejeElectronico = flejeElectronico;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
    
    
}
