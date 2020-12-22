package com.rmi_interface_impl;

import com.rmi_bean.FruitModel;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import com.rmi_interface.Compute;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class FruitComputeEngine extends UnicastRemoteObject implements Compute {

    Connection conn;

    public FruitComputeEngine() throws RemoteException {
           
        
        getConnection();
    
    }

    public void getConnection() throws RemoteException {
        try {
               
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmi", "root", "");
             System.out.println("con" + conn);
        
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
              System.out.println("SQLException" + e);
        }

    }

    @Override
    public void add(FruitModel fruit) throws RemoteException {
        try {
            System.out.println("adding" + conn);

            String q = "INSERT INTO fruits(fruit_name,price) values(?,?)";
            PreparedStatement pst = conn.prepareStatement(q);
            pst.setString(1, fruit.getFruitName());
            pst.setString(2, fruit.getPrice());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("we are her");
        }

    }

    @Override
    public void update(FruitModel fruit) throws RemoteException {

        try {
            String q = "Update fruits set fruit_name =?,price=? where id = ? ";
            PreparedStatement pst = conn.prepareStatement(q);
            pst.setString(1, fruit.getFruitName());
            pst.setString(2, fruit.getPrice());
            pst.setInt(3, Integer.parseInt(fruit.getId()));
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FruitComputeEngine.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(FruitModel fruit) throws RemoteException {
        try {
            String q = "delete from fruits where id = ? ";
            PreparedStatement pst = conn.prepareStatement(q);
            pst.setInt(1, Integer.parseInt(fruit.getId()));
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FruitComputeEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String fetch() throws RemoteException {
        
      
        String p = "";
        try {
            String q = "select id,fruit_name,price from fruits";
            PreparedStatement pst = conn.prepareStatement(q);           
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                p +=rs.getString("fruit_name")+"BR"+rs.getString("price")+"BR"+rs.getString("id")+"EN";

            }


            
        } catch (SQLException ex) {
            System.out.println("errrorrr====="+ex.getMessage());
            Logger.getLogger(FruitComputeEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public String computeCost() throws RemoteException {
        
        String p = "";
        try {
            String q = "select sum(price) as cost from fruits";
            PreparedStatement pst = conn.prepareStatement(q);           
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                p = rs.getString("cost");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FruitComputeEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

}
