package hw.hw2;

import java.util.Random;

public abstract class Player {

    private int chipCount;
    protected String name;

    public Player() {
        chipCount = 0;
    }

    public String getName() {
        return name;
    }

    public int getChipCount() {
        return chipCount;
    }

    protected void addChips(int numChips) {
        chipCount += numChips;
    }

    protected int rollDice() {
        Random generator = new Random();
        return generator.nextInt(4) + 1;
    }

    public abstract int makeMove(int potSize);
}