package hw.hw8;

import java.util.*;

public class CSVMachine {

    private State BeginField;
    private State UnquotedField;
    private State QuotedField;
    private State SeenAQuote;
    private State Error;
    private State state;
    private List<String> row;
    private StringBuilder sb;

    public CSVMachine() {
        this.BeginField = new BeginField(this);
        this.UnquotedField = new UnquotedField(this);
        this.QuotedField = new QuotedField(this);
        this.SeenAQuote = new SeenAQuote(this);
        this.Error = new Error(this);
        this.state = this.BeginField;
        this.row = new ArrayList<>();
        this.sb = new StringBuilder();
    }


    public void processChar(Character c) {
        if (c == '"') {
            state.quote(c);
        } else if (c == ',') {
            state.comma(c);
        } else {
            state.other(c);
        }
    }

    public void appendChar(Character c) {
        sb.append(c);
    }

    public void endOfString() {
        if (this.state == getError()) {
            row.add("ERROR");
        } else {
            row.add(sb.toString());
        }
        sb = new StringBuilder();
    }


    // Getters
    public State getBeginField() { return this.BeginField; }
    public State getUnquotedField() { return this.UnquotedField; }
    public State getQuotedField() { return this.QuotedField; }
    public State getSeenAQuote() { return this.SeenAQuote; }
    public State getError() { return this.Error; }

    public List<String> getRow() {
        if (sb.length() != 0) { endOfString(); }
        return row;
    }

    // Setters
    public void setState(State state) { this.state = state; }

}
