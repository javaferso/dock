/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smu.vision;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JFerreira
 */
public class Breadcrumb {
    private String title;
    private String url;
    
    public Breadcrumb(){
        
    }

    public Breadcrumb(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
    
    public static List<Breadcrumb> generateBreadcrumbs(String currentUrl) {
        List<Breadcrumb> breadcrumbs = new ArrayList<>();
        String[] pathParts = currentUrl.split("/");

        StringBuilder urlBuilder = new StringBuilder();
        for (String part : pathParts) {
            if (!part.isEmpty()) {
                urlBuilder.append("/").append(part);
                breadcrumbs.add(new Breadcrumb(capitalize(part.replace(".jsp", "").replace("-", " ")), urlBuilder.toString()));
            }
        }

        return breadcrumbs;
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    
}
