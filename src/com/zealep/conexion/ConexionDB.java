/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author cpelaez
 */

public class ConexionDB {
    
     Connection conexion = null;
     static ConexionDB instance = null;

    public ConexionDB() {
        try {
        
        String host ="localhost";
        String port="3306";
        String database="dbdentista";
        String user="root";
        String password="1234";
        
        String url = "jdbc:mysql://" + host +":" + port +"/"+database;
        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(url, user, password);
            
        } catch (ClassNotFoundException | SQLException e) {
                
        }
      }
     
    public Connection getConexion()
    {
        return conexion;
    }
     
    public static ConexionDB getInstace()
    {   instance = new ConexionDB();
        return instance;
    }
    
     @Override
    protected void finalize() throws Throwable {
        if (!conexion.isClosed()) {
            conexion.close();
            conexion = null;
        }
        super.finalize();
    }
    
}
