// author: Rootul Patel & Nelson Demoraes
// file: ScoreAndLine.java
// date: 5/2/14
// ps10
// Squares ScoreAndLine
package players.player1;
import players.*;
import board.*;
import util.*;
import java.util.*;
import java.awt.Color;
public final class ScoreAndLine {
    private Score first;
    private Line second;
    public ScoreAndLine(Score first, Line second) {
        this.first = first;
        this.second = second;
    }
    public Score getScore() {
        return first;
    }
    public Line getLine() {
        return second;
    }
}