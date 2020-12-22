package com.start_server;

import com.rmi_interface_impl.FruitComputeEngine;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FruitComputeTaskRegistry {

    public static void main(String[] args) throws RemoteException {

        Registry reg = LocateRegistry.createRegistry(1099);
        FruitComputeEngine service = new FruitComputeEngine();
        reg.rebind("SERVER", service);
        System.out.println("Server has started" + reg);

    }

}
