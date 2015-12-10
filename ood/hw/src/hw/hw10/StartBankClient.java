package hw.hw10;

import java.rmi.*;

public class StartBankClient {

    public static void main(String args[]) throws Exception {
        Service service = (Service) Naming.lookup("rmi://localhost/bank");
        BankView view = new BankView();
        BankController controller = new BankController(service, view);
    }

}