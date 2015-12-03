package hw.hw2;

import java.util.Scanner;

public class HumanPlayer extends Player {

    private static Scanner reader = new Scanner(System.in);
    private static int num = 1;

    public HumanPlayer() {
        super();
        name = "HumanPlayer" + num++;
    }

    public int makeMove(int potSize) {
        int numRolls = 1;
        int potSizeIncrement = 2;

        while (true) {
            int diceRoll = rollDice();

            if (diceRoll == 1) {
                System.out.println(getName() + " aced out after " + numRolls + " rolls");
                break;
            } else {
                System.out.println(getName() + ": rolled a " + diceRoll + ". Pot size: " + potSize);
                System.out.println(getName() + ": Press 1 to take pot, 2 to reroll");

                int userInput = reader.nextInt();
                if (userInput == 1) {
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