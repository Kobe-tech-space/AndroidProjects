package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_result;
    private String firstNum="";
    private String operator="";
    private String secondNum="";
    private String result="";
    private String showText="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_result=findViewById(R.id.tv_result);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_divide).setOnClickListener(this);
        findViewById(R.id.btn_multiply).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_seven).setOnClickListener(this);
        findViewById(R.id.btn_eight).setOnClickListener(this);
        findViewById(R.id.btn_nine).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.btn_four).setOnClickListener(this);
        findViewById(R.id.btn_five).setOnClickListener(this);
        findViewById(R.id.btn_six).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_three).setOnClickListener(this);
        findViewById(R.id.btn_reciprocal).setOnClickListener(this);
        findViewById(R.id.btn_zero).setOnClickListener(this);
        findViewById(R.id.btn_dot).setOnClickListener(this);
        findViewById(R.id.btn_equal).setOnClickListener(this);
        findViewById(R.id.ib_sqrt).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        String inputText;
        if(v.getId() == R.id.ib_sqrt) {
            inputText="√";
        }
        else {
            inputText=((TextView) v).getText().toString();
        }
        switch (v.getId()) {
            case R.id.btn_clear:
                clear();
                break;
            case R.id.btn_cancel:
                if (showText.length() > 0) {
                    String newText = showText.substring(0, showText.length() - 1);
                    if (newText.isEmpty()) {
                        clear();
                    } else {
                        refreshText(newText);
                        if (operator.equals("")) {
                            firstNum = newText;
                        } else {
                            secondNum = newText;
                        }
                    }
                }
                break;
            case R.id.btn_plus:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                operator = inputText;
                refreshText(showText + operator);
                break;
            case R.id.btn_equal:
                double calculate_result = calculateFour();
                refreshOperate(String.valueOf(calculate_result));
                refreshText(showText + "=" + result);
                break;
            case R.id.ib_sqrt:
                double sqrt_result = Math.sqrt(Double.parseDouble(firstNum));
                refreshOperate(String.valueOf(sqrt_result));
                refreshText(showText + "√=" + result);
                break;
            default:
                if(result.length()  > 0 && operator.equals("")) {
                    clear();
                }
                if(operator.equals("")) {
                    firstNum=firstNum+inputText;
                } else {
                    secondNum=secondNum+inputText;
                }
                if(showText.equals("0") && !inputText.equals(".")) {
                    refreshText(inputText);
                } else {
                    refreshText(showText + inputText);
                }
                break;
        }
    }
    private double calculateFour() {
        switch (operator) {
            case "+":
                return Double.parseDouble(firstNum) + Double.parseDouble(secondNum);
            case "-":
                return Double.parseDouble(firstNum) - Double.parseDouble(secondNum);
            case "*":
                return Double.parseDouble(firstNum) * Double.parseDouble(secondNum);
            default:
                return Double.parseDouble(firstNum) / Double.parseDouble(secondNum);
        }
    }
    private void clear() {
        firstNum = "";
        operator = "";
        secondNum = "";
        result = "";
        refreshText("0");
    }
    private void refreshOperate(String new_result) {
        result=new_result;
        firstNum=result;
        secondNum="";
        operator="";
    }
    private void refreshText(String text) {
        showText=text;
        tv_result.setText(showText);
    }
}