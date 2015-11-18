package hw.hw8;

public class UnquotedField implements State {

    private CSVMachine machine;

    public UnquotedField(CSVMachine machine) {
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
        machine.appendChar(c);
        machine.setState(machine.getUnquotedField());
    }

}
