package hw.hw10;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class StartBankService {

    public static void main(String args[]) throws Exception {
        try {
            // create the rmi registry if it doesn't exist
            Registry reg = LocateRegistry.createRegistry(1099);
        }
        catch (RemoteException e) {}

        BankService s = new BankService();
        Naming.rebind("bank", s);
    }

}
