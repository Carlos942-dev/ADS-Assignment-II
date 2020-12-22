package com.rmi_interface;

import com.rmi_bean.FruitModel;
import java.rmi.*;

public interface Compute extends Remote {
    public void add(FruitModel fruit) throws RemoteException;
    public void update(FruitModel fruit) throws RemoteException;
    public void delete(FruitModel fruit) throws RemoteException;
    public String fetch() throws RemoteException;
    public String computeCost() throws RemoteException;
    
}