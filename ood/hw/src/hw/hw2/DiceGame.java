package hw.hw2;

import java.util.List;

public class DiceGame {

    private List<Player> Players;
    private int potSize = 0;

    public DiceGame(List<Player> players) {
        Players = players;
    }

    public void start() {
        int currPlayerIndex = 0;

        while (true){
            Player currPlayer = Players.get(currPlayerIndex);
            incrementPot();
            potSize = currPlayer.makeMove(potSize);
            printChipCounts();
            printPotSize();

            if (currPlayer.getChipCount() >= 30) {
                break;
            }

            currPlayerIndex += 1;
            if (currPlayerIndex >= Players.size()) {
                currPlayerIndex = 0;
            }

            System.out.println();
        }
    }

    private void printChipCounts() {
        System.out.print("Chip count: ");
        for (Player player: Players) {
            System.out.print(player.getName() + ":" + player.getChipCount() + " ");
        }
        System.out.println();
    }

    private void printPotSize() {
        System.out.println("The pot contains " + potSize + " chips.");
    }

    private void incrementPot() {
        potSize += 1;
    }

}