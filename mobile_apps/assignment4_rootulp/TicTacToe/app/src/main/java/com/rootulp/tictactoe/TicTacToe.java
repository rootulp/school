package com.rootulp.tictactoe;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class TicTacToe extends ActionBarActivity {


    Button Array[][];
    Button NewGame;
    int turn=1;
    int i, k, tietotalx=0, tietotalo=0;
    int p2wins1=0,p2wins2=0,p2wins3=0,p2wins4=0,p2wins5=0,p2wins6=0,p2wins7=0,p2wins8=0,p1wins1=0,p1wins2=0,p1wins3=0,p1wins4=0,p1wins5=0,p1wins6=0,p1wins7=0,p1wins8=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        SetUp();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tic_tac_toe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





    public void Turn(View view) {
        int buttonId = view.getId();
        switch (buttonId) {
            case R.id.button1:
                if (Array[1][1].isEnabled()) {
                    Array[1][1].setEnabled(false);
                    if (turn==1){
                        Array[1][1].setText("X");
                        p1wins1++;
                        p1wins4++;
                        p1wins7++;
                        checkBoard();
                        turn=2;}
                    else{
                        Array[1][1].setText("O");
                        p2wins1++;
                        p2wins4++;
                        p2wins7++;
                        checkBoard();
                        turn=1;
                    }
                }
                break;
            case R.id.button2:
                if (Array[1][2].isEnabled()) {
                    Array[1][2].setEnabled(false);
                    if (turn==1){
                        Array[1][2].setText("X");
                        p1wins2++;
                        p1wins4++;
                        checkBoard();
                        turn=2;}
                    else{
                        Array[1][2].setText("O");
                        p2wins2++;
                        p2wins4++;
                        checkBoard();
                        turn=1;
                    }
                }
                break;
            case R.id.button3:
                if (Array[1][3].isEnabled()) {
                    Array[1][3].setEnabled(false);
                    if (turn==1){
                        Array[1][3].setText("X");
                        p1wins3++;
                        p1wins4++;
                        p1wins8++;
                        checkBoard();
                        turn=2;}
                    else{
                        Array[1][3].setText("O");
                        p2wins3++;
                        p2wins4++;
                        p2wins8++;
                        checkBoard();
                        turn=1;
                    }
                }
                break;
            case R.id.button4:
                if (Array[2][1].isEnabled()) {
                    Array[2][1].setEnabled(false);
                    if (turn==1){
                        Array[2][1].setText("X");
                        p1wins1++;
                        p1wins5++;
                        checkBoard();
                        turn=2;}
                    else{
                        Array[2][1].setText("O");
                        p2wins1++;
                        p2wins5++;
                        checkBoard();
                        turn=1;
                    }
                }
                break;
            case R.id.button5:
                if (Array[2][2].isEnabled()) {
                    Array[2][2].setEnabled(false);
                    if (turn==1){
                        Array[2][2].setText("X");
                        p1wins2++;
                        p1wins5++;
                        p1wins8++;
                        p1wins7++;
                        checkBoard();
                        turn=2;}
                    else{
                        Array[2][2].setText("O");
                        p2wins2++;
                        p2wins5++;
                        p2wins8++;
                        p2wins7++;
                        checkBoard();
                        turn=1;
                    }
                }
                break;
            case R.id.button6:
                if (Array[2][3].isEnabled()) {
                    Array[2][3].setEnabled(false);
                    if (turn==1){
                        Array[2][3].setText("X");
                        p1wins3++;
                        p1wins5++;
                        checkBoard();
                        turn=2;}
                    else{
                        Array[2][3].setText("O");
                        p2wins3++;
                        p2wins5++;
                        checkBoard();
                        turn=1;
                    }
                }
                break;
            case R.id.button7:
                if (Array[3][1].isEnabled()) {
                    Array[3][1].setEnabled(false);
                    if (turn==1){
                        Array[3][1].setText("X");
                        p1wins8++;
                        p1wins1++;
                        p1wins6++;
                        checkBoard();
                        turn=2;}
                    else{
                        Array[3][1].setText("O");
                        p2wins8++;
                        p2wins1++;
                        p2wins6++;
                        checkBoard();
                        turn=1;
                    }
                }
                break;
            case R.id.button8:
                if (Array[3][2].isEnabled()) {
                    Array[3][2].setEnabled(false);
                    if (turn==1){
                        Array[3][2].setText("X");
                        p1wins2++;
                        p1wins6++;
                        checkBoard();
                        turn=2;}
                    else{
                        Array[3][2].setText("O");
                        p2wins2++;
                        p2wins6++;
                        checkBoard();
                        turn=1;
                    }
                }
                break;
            case R.id.button9:
                if (Array[3][3].isEnabled()) {
                    Array[3][3].setEnabled(false);
                    if (turn==1){
                        Array[3][3].setText("X");
                        p1wins3++;
                        p1wins7++;
                        checkBoard();
                        turn=2;}
                    else{
                        Array[3][3].setText("O");
                        p2wins3++;
                        p2wins7++;
                        checkBoard();
                        turn=1;
                    }
                }
                break;

        }}






    public void SetUp() {
        p2wins1=0;p2wins2=0;p2wins3=0;p2wins4=0;p2wins5=0;p2wins6=0;p2wins7=0;p2wins8=0;p1wins1=0;p1wins2=0;p1wins3=0;p1wins4=0;p1wins5=0;p1wins6=0;p1wins7=0;p1wins8=0;
        Array = new Button[9][9];

        Array[1][1] = (Button) findViewById(R.id.button1);
        Array[1][2] = (Button) findViewById(R.id.button2);
        Array[1][3] = (Button) findViewById(R.id.button3);
        Array[2][1] = (Button) findViewById(R.id.button4);
        Array[2][2] = (Button) findViewById(R.id.button5);
        Array[2][3] = (Button) findViewById(R.id.button6);
        Array[3][1] = (Button) findViewById(R.id.button7);
        Array[3][2] = (Button) findViewById(R.id.button8);
        Array[3][3] = (Button) findViewById(R.id.button9);

        for (i = 1; i <= 3; i++) {
            for (k = 1; k <= 3; k++) {
                if (!Array[i][k].isEnabled()) {
                    Array[i][k].setText(" ");
                    Array[i][k].setEnabled(true);
                }
            }
        }}

    public void checkBoard() {
        if (p2wins1 == 3 || p2wins2 == 3 || p2wins3 == 3 || p2wins4 == 3 || p2wins5 == 3 || p2wins6 == 3 || p2wins7 == 3 || p2wins8 == 3) {
            Toast.makeText(getApplicationContext(), "Player 2 Wins!", Toast.LENGTH_SHORT).show();
            for (i = 1; i <= 3; i++) {
                for (k = 1; k <= 3; k++) {
                    if (Array[i][k].isEnabled()) {
                        Array[i][k].setEnabled(false);
                    }}}
            return;
        }
        if (p1wins1 == 3 || p1wins2 == 3 || p1wins3 == 3 || p1wins4 == 3 || p1wins5 == 3 || p1wins6 == 3 || p1wins7 == 3 || p1wins8 == 3) {
            Toast.makeText(getApplicationContext(), "Player 1 Wins!", Toast.LENGTH_SHORT).show();
            for (i = 1; i <= 3; i++) {
                for (k = 1; k <= 3; k++) {
                    if (Array[i][k].isEnabled()) {
                        Array[i][k].setEnabled(false);
                    }}}
            return;
        }
        if (
                !Array[1][2].isEnabled() && !Array[1][2].isEnabled() && !Array[1][3].isEnabled() &&  !Array[2][1].isEnabled() && !Array[2][2].isEnabled() && !Array[2][3].isEnabled() && !Array[3][1].isEnabled() && !Array[3][2].isEnabled() && !Array[3][3].isEnabled()){

            Toast.makeText(getApplicationContext(), "Tie Game, Press New Game!", Toast.LENGTH_SHORT).show();



        }

    }

    public void Reset(View view){
        turn=1;p2wins1=0;p2wins2=0;p2wins3=0;p2wins4=0;p2wins5=0;p2wins6=0;p2wins7=0;p2wins8=0;p1wins1=0;p1wins2=0;p1wins3=0;p1wins4=0;p1wins5=0;p1wins6=0;p1wins7=0;p1wins8=0;
        Toast.makeText(getApplicationContext(), "NewGameCalled", Toast.LENGTH_SHORT).show();
        int i=0,k=0;
        for (i = 1; i <= 3; i++) {
            for (k = 1; k <= 3; k++) {
                Array[i][k].setText(" ");
                Array[i][k].setEnabled(true);

            }}
    }




}
