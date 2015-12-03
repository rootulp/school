package hw.hw2;

public class ConservativeComputerPlayer extends ComputerPlayer {

    private static int num = 1;

    public ConservativeComputerPlayer() {
        super();
        name = "ConservativeComputerPlayer" + num++;
    }

    public int makeMove(int potSize) {
        int diceRoll = rollDice();

        if (diceRoll == 1) {
            System.out.println(getName() + " aced out after 1 rolls");
            return potSize;
        } else {
            System.out.println(getName() + " stopped after 1 rolls and won " + potSize + " chips");
            addChips(potSize);
            return 0;
        }
    }
}