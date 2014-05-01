package players.player2;

import players.*;
import board.*;
import util.*;
import java.util.*;
import java.awt.Color;

public class Player2 implements Player {
 
    private DBG dbg;

    public Player2() {
        dbg = new DBG(DBG.PLAYERS,"Player2");
    }
    
    public Line makePlay(Board board, Player opponent, Line oppPlay, Clock clock) {
        Set<Line> lines = board.openLines();
        if(dbg.debug)
            dbg.println("makePlay: player2 thinks there are " + lines.size() + " open lines");
        
        int pick = (int) (Math.random() * lines.size()),
            i = 0;
        for(Line line : lines) {
            if(i == pick) return line;
            i = i + 1;
        }
        return new LineC(0, 0, Side.NORTH);
    }
    public String teamName() { return "Terriers"; }
    public Color getColor()  { return Util.PLAYER2_COLOR; }    
    public int getId()       { return 2; }
    public String toString() { return teamName(); }
}