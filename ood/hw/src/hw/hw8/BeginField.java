package hw.hw8;

public class BeginField implements State {

    private CSVMachine machine;

    public BeginField(CSVMachine machine) {
        this.machine = machine;
    }

    public void quote(Character c) {
        machine.setState(machine.getQuotedField());
    }

    public void comma(Character c) {
        machine.endOfString();
        machine.setState(machine.getBeginField());
    }

    public void other(Character c) {
        machine.appendChar(c);
        machine.setState(machine.getUnquotedField());
    }

}
