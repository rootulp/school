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
        Set<Line> lines = board.openLines();
        
        //Util.sleep(100); // Right now Player1 is waiting 1 second.
        
        if(dbg.debug) {
            dbg.println("makePlay: " + lines.size() + " open lines.");
            dbg.println("makePlay: lines = " + lines.toString());
        }
            
        int pick = (int) (Math.random() * lines.size()),
            i = 0;
        for(Line line : lines) {
            if(i == pick) return line;
            i = i + 1;
        }
        return new LineC(0, 0, Side.NORTH);
    }
    public String teamName() { return "Tummy"; }
    public Color getColor()  { return Util.PLAYER1_COLOR; }
    public int getId()       { return 1; }
    public String toString() { return teamName(); }
}