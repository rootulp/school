package hw.hw2;

import java.util.List;
import java.util.ArrayList;

public class HW2DiceGame {

    private static String[] playerTypes = {"HumanPlayer", "ConservativeComputerPlayer", "AggressiveComputerPlayer"};

    public static void main(String[] args) {
        List<Player> Players = new ArrayList<Player>();

        for (String player : playerTypes) {
            if (player == "HumanPlayer") {
                Players.add(new HumanPlayer());
            } else if (player == "ConservativeComputerPlayer") {
                Players.add(new ConservativeComputerPlayer());
            } else if (player == "AggressiveComputerPlayer") {
                Players.add(new AggressiveComputerPlayer());
            } else {
                System.out.println("Invalid Player!");
            }
        }

        DiceGame game = new DiceGame(Players);
        game.start();
    }

}