package com.example.rootulp.assignment1_rootulp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {
    public String str ="";
    Character op = 'q';
    float num, numtemp;
    boolean decimal = false;
    EditText showResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showResult = (EditText)findViewById(R.id.result_id);
    }

    // all digit clicks come here
    public void clickDigit(View view) {
        int buttonId = view.getId();
        switch (buttonId) {
            case R.id.button0:
                insert('0');
                break;
            case R.id.button1:
                insert('1');
                break;
            case R.id.button2:
                insert('2');
                break;
            case R.id.button3:
                insert('3');
                break;
            case R.id.button4:
                insert('4');
                break;
            case R.id.button5:
                insert('5');
                break;
            case R.id.button6:
                insert('6');
                break;
            case R.id.button7:
                insert('7');
                break;
            case R.id.button8:
                insert('8');
                break;
            case R.id.button9:
                insert('9');
                break;
        }
    }

    // all operator clicks come here
    public void clickOP(View view) {
        int buttonId = view.getId();
        switch (buttonId) {
            case R.id.button_add:
                perform();
                op = '+';
                break;
            case R.id.button_subtract:
                perform();
                op = '-';
                break;
            case R.id.button_equals:
                calculate();
                break;
            case R.id.button_divide:
                perform();
                op = '/';
                break;
            case R.id.button_multiply:
                perform();
                op = '*';
                break;
            case R.id.button_decimal:
                insert('.');
                break;
            case R.id.button_sign:
                switch_sign();
                break;
            case R.id.button_clear:
                reset();
                break;
        }
    }

    private void reset() {
        str ="";
        op ='q';
        num = 0;
        numtemp = 0;
        decimal = false;
        showResult.setText("");
    }

    private void switch_sign() {
        // TODO Auto-generated method stub
        num *= -1;
        output(num);
    }

    private void insert(Character digit) {
        // TODO Auto-generated method stub
        // Don't insert decimal if output already contains decimal point
        if (digit == '.') {
            if (!decimal) {
                decimal = true;
                str = str + digit.toString();
                num = Float.valueOf(str);
                output(num);
            }
        } else {
            str = str + digit.toString();
            num = Float.valueOf(str);
            output(num);
        }
    }

    private void perform() {
        // TODO Auto-generated method stub
        str = "";
        numtemp = num;
        decimal = false;
    }

    private void calculate() {
        // TODO Auto-generated method stub
        if(op == '+')
            num = numtemp+num;
        else if(op == '-')
            num = numtemp-num;
        else if(op == '/')
            num = numtemp/num;
        else if(op == '*')
            num = numtemp*num;
        output(num);
    }

    private boolean isNan(float val){
        return val != val;
    }


    private void output(float num) {
        if(num == Float.POSITIVE_INFINITY || num == Float.NEGATIVE_INFINITY) {
            showResult.setText("Error:Infinity");
        } else if (isNan(num)) {
            showResult.setText("Error:NaN");
        } else {
            showResult.setText("" + num);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
