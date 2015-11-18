package hw.hw8;

public class SeenAQuote implements State {

    private CSVMachine machine;

    public SeenAQuote(CSVMachine machine) {
        this.machine = machine;
    }

    public void quote(Character c) {
        machine.appendChar(c);
        machine.setState(machine.getQuotedField());
    }

    public void comma(Character c) {
        machine.endOfString();
        machine.setState(machine.getBeginField());
    }

    public void other(Character c) {
        machine.setState(machine.getError());
    }

}
