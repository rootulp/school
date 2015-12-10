package hw.hw10;

import java.rmi.*;

public interface Service extends Remote {
    BankAccount getAccount(String userName) throws RemoteException;
    void newAccount(String userName) throws RemoteException;
    void deposit(String userName, Integer amount) throws RemoteException;
    void withdraw(String userName, Integer amount) throws RemoteException;
}
