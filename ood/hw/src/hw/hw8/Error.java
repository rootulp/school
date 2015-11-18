package hw.hw8;

public class Error implements State {

    private CSVMachine machine;

    public Error(CSVMachine machine) {
        this.machine = machine;
    }

    public void quote(Character c) {
        machine.setState(machine.getError());
    }

    public void comma(Character c) {
        machine.endOfString();
        machine.setState(machine.getBeginField());
    }

    public void other(Character c) {
        machine.setState(machine.getError());
    }

}
