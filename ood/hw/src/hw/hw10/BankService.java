package hw.hw10;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.LinkedList;

public class BankService extends UnicastRemoteObject implements Service {

    private List<BankAccount> accounts;

    public BankService() throws RemoteException {
        this.accounts = new LinkedList();
    }

    public BankAccount getAccount(String username) throws RemoteException {
        for(BankAccount account : accounts) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }

    public void newAccount(String userName) throws RemoteException {
        accounts.add(new BankAccount(userName));
    }

    public void deposit(String userName, Integer amount) throws RemoteException {
        getAccount(userName).deposit(amount);
    }

    public void withdraw(String userName, Integer amount) throws RemoteException {
        getAccount(userName).withdraw(amount);
    }

}
