package hw.hw8;

public class QuotedField implements State {

    private CSVMachine machine;

    public QuotedField(CSVMachine machine) {
        this.machine = machine;
    }

    public void quote(Character c) {
        machine.setState(machine.getSeenAQuote());
    }

    public void comma(Character c) {
        machine.appendChar(c);
        machine.setState(machine.getQuotedField());
    }

    public void other(Character c) {
        machine.appendChar(c);
        machine.setState(machine.getQuotedField());
    }

}
