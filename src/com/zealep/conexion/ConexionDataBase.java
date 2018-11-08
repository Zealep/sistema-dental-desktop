/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zealep.conexion;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import java.sql.Connection;

/**
 *
 * @author user
 */
public class ConexionDataBase {
     private static Connection conection=null;
    public Connection getConection(){
        try{
            MysqlConnectionPoolDataSource ds=new MysqlConnectionPoolDataSource();
            ds.setServerName("localhost");
            ds.setPort(3306);
            ds.setDatabaseName("dbdentista");
            conection=ds.getConnection("root","1234");
        }catch(Exception ex){
           ex.printStackTrace();
        }
        return conection;
    }
}
