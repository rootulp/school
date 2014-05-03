// author: Rootul Patel & Nelson Demoraes
// file: Player2.java
// date: 5/2/14
// ps10
// Squares Player1
package players.player1;
import players.*;
import board.*;
import util.*;
import java.util.*;
import java.awt.Color;
public class Player1 implements Player {
    private DBG dbg;
    public Player1() {
        dbg = new DBG(DBG.PLAYERS, "Player1");
    }
    public Line makePlay(Board board, Player opponent, Line oppPlay, Clock clock) {
        
        //This is our simple greedy player. We spent a lot of time working on a minimax 
        //algorithm but couldn't get it to work in time. We decided to submit a functioning
        //player to work in the tournament along with the minimax attempt in comments

        //==================== MINIMAX Attempt======================
        //        ScoreAndLine result = this.minMax(board, 0, 2, opponent);
        //        Line resultLine = result.getLine();
        //        System.out.println("About to return line:" + resultLine);
        //        return resultLine;
        
        Set<Line> lines = board.openLines();
        if (lines.size() == 1) {
            return lines.iterator().next();
        }

        int bestValue = -1;
        Line lbest = null;
        for (Line line : lines) {
            board.markLine(this, line);
            Score s = board.getScore();
            if (s.getPlayer1() > bestValue) {
                bestValue = s.getPlayer1();
                lbest = line;
            }
            board.removeLine(line);
        }
        return lbest;
        
    }
    public String teamName() {
        return "Tummy";
    }
    public Color getColor() {
        return Util.PLAYER1_COLOR;
    }
    public int getId() {
        return 1;
    }
    public String toString() {
        return teamName();
    }   

//=================== REST OF MINIMAX ATTEMPT ===========================
//    private ScoreAndLine minMax(Board board, int seed, int depth, Player opponent) {
//        Score bestScore = null;
//        Line lbest = null;
//        int val1 = -1;
//        int val2 = 100;
//        if (depth == 0 || board.gameOver()) { //if depth ==0  
//            System.out.println("Reached bottom");
//            Score s = board.getScore();
//            Line l = null;
//            return new ScoreAndLine (s, l);
//        } else {
//            Set<Line> lines = board.openLines();
//            for (Line line : lines) {
//                if (seed == 0 ) {
//                    board.markLine(this, line);
//                    System.out.println("Marked " + line + "for: " + this);
//                    ScoreAndLine sl = minMax(board, 1, depth-1, opponent);
//                    Score s = sl.getScore();
//                    Line l = sl.getLine();
//                    System.out.println("s1" + s.getPlayer1() + "s2" + s.getPlayer2());
//                    if ((s.getPlayer1() - s.getPlayer2()) > val1) {
//                        val1 = s.getPlayer1() - s.getPlayer2();
//                        bestScore = s;
//                        lbest = l;    
//                        System.out.println("Best Score: " + bestScore + " Line: " + lbest);
//                    }
//                    board.removeLine(line);
//                    System.out.println("Removed " + line);
//                } else {
//                    board.markLine(opponent, line);
//                    System.out.println("Marked " + line + "for: " + opponent);
//                    ScoreAndLine sl = minMax(board, 0, depth-1, opponent);
//                    Score s = sl.getScore();
//                    Line l = sl.getLine();
//                    System.out.println("s1" + s.getPlayer1() + "s2" + s.getPlayer2());
//                    if ((s.getPlayer1() - s.getPlayer2()) < val2) {
//                        val2 = s.getPlayer1() - s.getPlayer2();
//                        bestScore = s;
//                        lbest = l;
//                        System.out.println("Best Score: " + bestScore + " Line: " + lbest);
//                    }
//                    board.removeLine(line);
//                    System.out.println("Removed" + line);
//                }
//            }
//            return new ScoreAndLine (bestScore, lbest); //lbest is always null?!
//        }
//    }
       
}