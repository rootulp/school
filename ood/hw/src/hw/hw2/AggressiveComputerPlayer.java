package hw.hw2;

public class AggressiveComputerPlayer extends ComputerPlayer {

    private static int num = 1;

    public AggressiveComputerPlayer() {
        super();
        name = "AggressiveComputerPlayer" + num++;
    }

    public int makeMove(int potSize) {
        int numRolls = 1;
        int potSizeIncrement = 2;

        while (true) {
            int diceRoll = rollDice();

            if (diceRoll == 1) {
                System.out.println(getName() + " aced out after " + numRolls + " rolls");
                break;
            }  else  {
                System.out.println(getName() + ": rolled a " + diceRoll + ". Pot size: " + potSize);

                if (potSize >= 5) {
                    System.out.println(getName() + " stopped after " + numRolls + " rolls and won " + potSize + " chips");
                    addChips(potSize);
                    potSize = 0;
                    break;
                } else {
                    numRolls += 1;
                    potSize += potSizeIncrement;
                    potSizeIncrement += 1;
                }
            }
        }

        return potSize;
    }
}
