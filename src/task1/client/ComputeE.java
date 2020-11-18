package task1.client;

import task1.compute.Compute;

import java.math.BigDecimal;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ComputeE {
    public static void main(String[] args) {
        if(System.getSecurityManager() == null){
            System.setSecurityManager(new SecurityManager());
        }
        try{
            Registry registry = LocateRegistry.getRegistry(args[0]);
            String name = "Compute";
            Compute comp = (Compute) registry.lookup(name);
            E task = new E(Integer.parseInt(args[1]));
            BigDecimal e = comp.executeTask(task);
            System.out.println("E: " + e);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
