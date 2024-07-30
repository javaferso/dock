/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smu.vision;


/**
 *
 * @author JFerreira
 */

import com.google.gson.JsonObject;

public class ServFlejesElectronicos {

    public JsonObject getDatosFlejeElectronico(String serverId) {
        JsonObject data = new JsonObject();

        switch (serverId) {
            case "037":
                data.addProperty("IpFlejeElectronico", "10.50.107.18");
                data.addProperty("IpConsultaPrecios", "10.50.105.200");
                data.addProperty("IpConsultaPrecios2", "10.50.105.201");
                break;
            case "362":
                data.addProperty("IpFlejeElectronico", "10.42.169.18");
                data.addProperty("IpConsultaPrecios", "10.50.105.202");
                data.addProperty("IpConsultaPrecios2", "10.50.105.203");
                break;
            case "917":
                data.addProperty("IpFlejeElectronico", "10.43.146.34");
                data.addProperty("IpConsultaPrecios", "10.50.105.204");
                data.addProperty("IpConsultaPrecios2", "10.50.105.205");
                break;
            default:
                data.addProperty("error", "Datos no disponibles para el servidor: " + serverId);
        }

        return data;
    }
}
