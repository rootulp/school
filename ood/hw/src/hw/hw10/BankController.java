package hw.hw10;

import java.rmi.RemoteException;

public class BankController {

    private Service service;
    private BankView view;
    private String userName;

    public BankController(Service service, BankView view) {
        this.service = service;
        this.view = view;
        view.addBankController(this);
    }

    public void setAccount(String userName) {
        this.userName = userName;
    }

    public void newAccount(String userName) {
        try {
            service.newAccount(userName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void deposit(Integer amount) {
        try {
            service.deposit(userName, amount);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void withdraw(Integer amount) {
        try {
            service.withdraw(userName, amount);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public Integer getBalance() {
        try {
            return service.getAccount(userName).getBalance();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

}

