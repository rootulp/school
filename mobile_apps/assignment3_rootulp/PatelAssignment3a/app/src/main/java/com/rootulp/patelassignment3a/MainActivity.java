package com.rootulp.patelassignment3a;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioButton;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    double totalIndividual = 0.00;
    double tipIndividual = 0.00;
    double tipAmount = 0.00;
    double tipPercentage = .00;
    double people = 1.00;
    double billFloat = 0.00;
    Spinner spinner;
    TextView totalEachOutput;
    TextView tipAmountOutput;
    EditText percent;
    EditText bill;
    RadioButton button10;
    RadioButton button15;
    RadioButton button20;
    SeekBar seek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button10 = (RadioButton) findViewById(R.id.button10);
        button15 = (RadioButton) findViewById(R.id.button15);
        button20 = (RadioButton) findViewById(R.id.button20);
        spinner = (Spinner) findViewById(R.id.spinner);

        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        addListenerOnSpinnerItemSelection();

        button10 = (RadioButton) findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                percent.setText(".10");
            }
        });


        button15 = (RadioButton) findViewById(R.id.button15);
        button15.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                percent.setText(".15");
            }
        });

        button20 = (RadioButton) findViewById(R.id.button20);
        button20.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                percent.setText(".20");
            }
        });

        seek = (SeekBar) findViewById(R.id.seekBar);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Double decimalPercent = Double.valueOf(progress) / 100.0;
                percent.setText(decimalPercent.toString());
            }
        });

        calculateDecimal();
    }

    public void clickButton(View view) {
        int buttonId = view.getId();
        switch (buttonId) {
            case R.id.clearButton:
                reset();
                break;
            case R.id.calculateDecimal:
                calculateDecimal();
                break;
            case R.id.calculateWhole:
                calculateWhole();
                break;
        }
    }

    private void reset() {
        totalEachOutput.setText("00.00");
        tipAmountOutput.setText("00.00");
        percent.setText("00.00");
        bill.setText("00.00");
    }

    private void calculateDecimal() {
        String Text = spinner.getSelectedItem().toString();
        people = Double.parseDouble(Text);
        totalEachOutput = (TextView) findViewById(R.id.totalEachOutput);
        tipAmountOutput = (TextView) findViewById(R.id.tipAmountOutput);
        percent = (EditText) findViewById(R.id.percent);
        bill = (EditText) findViewById(R.id.bill);
        billFloat = Double.parseDouble(bill.getText().toString());
        tipPercentage = Double.parseDouble(percent.getText().toString());

        tipAmount = ((billFloat * (1 + tipPercentage)) - billFloat);
        tipIndividual = (tipAmount / people);
        totalIndividual = (billFloat / people) + tipIndividual;

        totalEachOutput.setText(Double.toString(roundTwoDecimals(totalIndividual)));
        tipAmountOutput.setText(Double.toString(roundTwoDecimals(tipIndividual)));
    }

    private void calculateWhole() {
        calculateDecimal();
        double totalEach = Double.parseDouble(totalEachOutput.getText().toString());
        double tipAmount = Double.parseDouble(tipAmountOutput.getText().toString());

        tipAmountOutput.setText(Double.toString(roundWhole(tipAmount)));
        totalEachOutput.setText(Double.toString(roundWhole(totalEach)));
    }

    double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

    double roundWhole(double d) {
        return Math.ceil(d);
    }

    public void addListenerOnSpinnerItemSelection(){
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void addListenerOnButton() {
        spinner = (Spinner) findViewById(R.id.spinner);
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
