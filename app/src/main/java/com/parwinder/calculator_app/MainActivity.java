package com.parwinder.calculator_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

/**
 * Student name: Parwinder Deep Kaur
 * Student ID: A00237487
 * This class demonstrate the calculator app. It uses various operators to perform
 * calculations and if-else or switch statements.
 * It lets user to do some arithmetic and logical operations.
 * Operations supported are: Add, Subtract, Multiplication, Division
 * Under root, Square root, Modulous, Inverse
 *
 * @author Parwinder Deep Kaur
 * @version 1.0
 * @since 2 November, 2021
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button oneTV, twoTV, threeTV, fourTV, fiveTV,
            sixTV, sevenTV, eightTV, nineTV, zeroTV, decimalTV,
            addTV, subtractTV, multipleTV, divideTV, clearTV,
            percentageTV, squareTV, squareRootTV, inverseTV,
            equalsTV;
    private TextView displayTV, operatorTV;
    private float operand1, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewIds();
        setClickListeners();
    }

    /**
     * This is the overridden method of View class.
     * Using switch statement, it finds which view is clicked
     * execute the code accordingly and then break thw switch statement
     */
    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // case 1 to 10 shows the numbers clicked on the displayTV which is for showing operands and results
            case R.id.oneTV:
                displayTV.setText(displayTV.getText().toString() + oneTV.getText().toString());
                break;

            case R.id.twoTV:
                displayTV.setText(displayTV.getText().toString() + twoTV.getText().toString());
                break;

            case R.id.threeTV:
                displayTV.setText(displayTV.getText().toString() + threeTV.getText().toString());
                break;

            case R.id.fourTV:
                displayTV.setText(displayTV.getText().toString() + fourTV.getText().toString());
                break;

            case R.id.fiveTV:
                displayTV.setText(displayTV.getText().toString() + fiveTV.getText().toString());
                break;

            case R.id.sixTV:
                displayTV.setText(displayTV.getText().toString() + sixTV.getText().toString());
                break;

            case R.id.sevenTV:
                displayTV.setText(displayTV.getText().toString() + sevenTV.getText().toString());
                break;

            case R.id.eightTV:
                displayTV.setText(displayTV.getText().toString() + eightTV.getText().toString());
                break;

            case R.id.nineTV:
                displayTV.setText(displayTV.getText().toString() + nineTV.getText().toString());
                break;

            case R.id.zeroTV:
                displayTV.setText(displayTV.getText().toString() + zeroTV.getText().toString());
                break;

            case R.id.decimalTV:
                // check if the input numeric string already contains a decimal so that there can not be more than 1 decimal
                if (!displayTV.getText().toString().contains("."))
                    displayTV.append(String.format("%s", getString(R.string.dot))); // formats the string by appending a decimal (.)
                break;

            case R.id.addTV:
                setDisplay(getString(R.string.add_symbol));
                break;

            case R.id.subtractTV:
                setDisplay(getString(R.string.minus_symbol));
                break;

            case R.id.multiplyTV:
                setDisplay(getString(R.string.multiply_symbol));
                break;

            case R.id.divideTV:
                setDisplay(getString(R.string.divide_symbol));
                break;

            case R.id.percentageTV:
                if (!displayTV.getText().toString().isEmpty()) // check if text view is empty
                    setDisplay(getString(R.string.percentage));
                break;

            case R.id.squareTV:
                if (!displayTV.getText().toString().isEmpty())
                    findSquare();
                break;

            case R.id.square_rootTV:
                if (!displayTV.getText().toString().isEmpty())
                    findSquareRoot();
                break;

            case R.id.inverseTV:
                if (!displayTV.getText().toString().isEmpty())
                    inverse();
                break;

            case R.id.clearTV:
                resetDisplay();
                break;

            case R.id.equalsTV:
                showResult();
                break;
        }
    }


    /*This method calculates the mod of 2 operands/values. It calculates the value in
    float data type and then uses DecimalFormat to show only 4 digits after decimal.
    This set text as null and set hint as calculated result.*/
    private void inverse() {
        float value = Float.parseFloat(displayTV.getText().toString()); // parse string to float
        float result = 1 / value; // division
        displayTV.setText(null);
        displayTV.setHint(getFormat(result)); // call getFormat() method to get formatted decimal
    }


    /*This method calculates the square root of the input value. It make use of Math.sqrt()
     method to calculate the square root. After calculating it sets the result as hint to
     displayTV and clears the text of displayTV */
    private void findSquareRoot() {
        float value = Float.parseFloat(displayTV.getText().toString()); // parse string to float
        float result = (float) Math.sqrt(value); // inbuilt method of Math class
        displayTV.setText(null);
        displayTV.setHint(getFormat(result)); // call getFormat() method to get formatted decimal
    }


    /*This s method of String type. This uses subclass of NumberFormat that formats decimal numbers.
     * Returns the formatted decimal string in String data type*/
    private String getFormat(float result) {
        return new DecimalFormat("0.####").format(Float.parseFloat(String.valueOf(result)));
    }


    /*This method calculates the square of the input value.
     After calculating it sets the result as hint to displayTV and
     clears the text of displayTV */
    private void findSquare() {
        float value = Float.parseFloat(displayTV.getText().toString());
        float result = value * value; // multiplication
        displayTV.setText(null);
        displayTV.setHint(getFormat(result)); // call getFormat() method to get formatted decimal
    }


    /*This method resets the text views to initial values/null */
    private void resetDisplay() {
        displayTV.setText(null);
        operatorTV.setText(null);
        displayTV.setHint(getString(R.string._8_digit_calculator));
    }


    /*This method first check if there is some value input, then check if operator is set
     * This make use of calculateExpression() method to calculate the result of input value and
     * set the value as hint to displayTV
     * set text of operatorTV and displayTV as null*/
    private void showResult() {
        try {
            if (!displayTV.getText().toString().isEmpty()) {
                if (!operatorTV.getText().toString().isEmpty()) {
                    displayTV.setHint(getFormat(calculateExpression()));
                    displayTV.setText(null);
                    operatorTV.setText(null);
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.invalid_input), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    /*This methof sets the operand as 0 so that there is no previous value after calculation
     * sets the value of first operand in operand1 variable
     * sets the value of operatorTV and displayTV as empty if there is no first operand,
     * otherwise set value as hint to displayTV which is considered as first operand*/
    private void setDisplay(String operator) {
        operand1 = 0;
        if (!displayTV.getText().toString().equals("")) { // check if there is no user input value
            operand1 = Float.parseFloat(displayTV.getText().toString());
        }
        operatorTV.setText(operator);
        displayTV.setText(null);
        if (operand1 == 0) {
            operatorTV.setText(null);
            displayTV.setHint(getString(R.string._8_digit_calculator));
        } else {
            displayTV.setHint(getFormat(operand1));
        }
    }


    /*This method is to calculate(plus, minus, multiple, divide, mod) using 2 input values as operand1 and operand2
     * It make use of switch statement to find out which operator to calculate*/
    private float calculateExpression() {
        float operand2 = Float.parseFloat(displayTV.getText().toString());
        String operator = operatorTV.getText().toString();
        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;

            case "-":
                result = operand1 - operand2;
                break;

            case "*":
                result = operand1 * operand2;
                break;

            case "/":
                result = operand1 / operand2;
                break;

            case "mod":
                result = operand1 % operand2;
                if (result < 0)
                    result += operand2;
                break;
        }
        return result;
    }


    /*This methods find the views of all textviews */
    private void findViewIds() {
        oneTV = findViewById(R.id.oneTV);
        twoTV = findViewById(R.id.twoTV);
        threeTV = findViewById(R.id.threeTV);
        fourTV = findViewById(R.id.fourTV);
        fiveTV = findViewById(R.id.fiveTV);
        sixTV = findViewById(R.id.sixTV);
        sevenTV = findViewById(R.id.sevenTV);
        eightTV = findViewById(R.id.eightTV);
        nineTV = findViewById(R.id.nineTV);
        zeroTV = findViewById(R.id.zeroTV);
        decimalTV = findViewById(R.id.decimalTV);
        addTV = findViewById(R.id.addTV);
        subtractTV = findViewById(R.id.subtractTV);
        multipleTV = findViewById(R.id.multiplyTV);
        divideTV = findViewById(R.id.divideTV);
        clearTV = findViewById(R.id.clearTV);
        percentageTV = findViewById(R.id.percentageTV);
        squareTV = findViewById(R.id.squareTV);
        squareRootTV = findViewById(R.id.square_rootTV);
        inverseTV = findViewById(R.id.inverseTV);
        equalsTV = findViewById(R.id.equalsTV);
        displayTV = findViewById(R.id.displayTV);
        operatorTV = findViewById(R.id.operatorTV);
    }


    /*This method sets the click listener on all textviews*/
    private void setClickListeners() {
        oneTV.setOnClickListener(this);
        twoTV.setOnClickListener(this);
        threeTV.setOnClickListener(this);
        fourTV.setOnClickListener(this);
        fiveTV.setOnClickListener(this);
        sixTV.setOnClickListener(this);
        sevenTV.setOnClickListener(this);
        eightTV.setOnClickListener(this);
        nineTV.setOnClickListener(this);
        zeroTV.setOnClickListener(this);
        decimalTV.setOnClickListener(this);
        addTV.setOnClickListener(this);
        subtractTV.setOnClickListener(this);
        multipleTV.setOnClickListener(this);
        divideTV.setOnClickListener(this);
        clearTV.setOnClickListener(this);
        percentageTV.setOnClickListener(this);
        squareTV.setOnClickListener(this);
        squareRootTV.setOnClickListener(this);
        inverseTV.setOnClickListener(this);
        equalsTV.setOnClickListener(this);
    }
}