package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button button5, button6, button7, button8, button10, button11, button12, button13, button14, button15
            , button16,button17, button18, button19, button20, button21, button22, button23, button24 ;
    TextView textView;
    private String result = "0";
    Boolean flag1 = true;
    int count;
    public float calculate;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button10 = findViewById(R.id.button10);
        button11 = findViewById(R.id.button11);
        button12 = findViewById(R.id.button12);
        button13 = findViewById(R.id.button13);
        button14 = findViewById(R.id.button14);
        button15 = findViewById(R.id.button15);
        button16 = findViewById(R.id.button16);
        button17 = findViewById(R.id.button17);
        button18 = findViewById(R.id.button18);
        button19 = findViewById(R.id.button19);
        button20 = findViewById(R.id.button20);
        button21 = findViewById(R.id.button21);
        button22 = findViewById(R.id.button22);
        button23 = findViewById(R.id.button23);
        button24 = findViewById(R.id.button24);
        textView = findViewById(R.id.textView);
    }

    public void calculation(View view)
    {
        String operator ;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        Button currentButton = (Button) view;
        char buttonText = currentButton.getText().charAt(0);

        currentButton.setBackgroundResource(R.drawable.darkorange_circiular);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentButton.setBackgroundResource(R.drawable.circular);
            }
        },200);

        if(buttonText >= '0' && buttonText <= '9'){
            currentButton.setTextColor(getResources().getColor(R.color.dark_orange));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    currentButton.setTextColor(getResources().getColor(R.color.orange));
                }
            },200);
        }

        if((result.charAt(result.length()-1) == '/' || result.charAt(result.length()-1) == '+' ||
                result.charAt(result.length()-1) == '-' || result.charAt(result.length()-1) == '*')
            && (buttonText == '/' || buttonText == '+' || buttonText == '-' || buttonText == '*'))
        {
            result = result.substring(0,result.length()-1);
        }
        if(result.equals("0")) {
            if(buttonText == '/' || buttonText == '*')
                result = "0" +buttonText;
            else
                result = buttonText +"";
        }
        else {
            result = result + buttonText;
        }
        float val1,val2;
        if(buttonText == 'C'){
            result = "0";
        }
        if(view.getId() == R.id.button10) {
            if(result.length()<=2)
                result = "0";
            else
                result = result.substring(0,result.length()-2);
        }
        String[] perform = result.split("[-+/*=%]");
        for (int i = 0; i<result.length()-1;i++) {
            if(buttonText == '/' || buttonText == '+' || buttonText == '-' || buttonText == '*') {
                count++;
            }
        }
        if(result.charAt(0) == '-' && count>=3) {
            count=1;
            operator = String.valueOf(result.charAt(perform[1].length()+1));
            val1 = Float.parseFloat(perform[1]);
            val2 = Float.parseFloat(perform[2]);
            calculate = equals(val1,val2,operator,'-');
            result = decimalFormat.format(calculate)+buttonText ;
        }
        else if(perform.length>=2) {
            count=0;
            if(perform[0].length() == 0) {
                val1 = 0;
            } else {
                val1 = Float.parseFloat(perform[0]);
            }
            if(perform[1].length() == 0) {
                val2 = 0;
            } else {
                val2 = Float.parseFloat(perform[1]);
            }
            operator = String.valueOf(result.charAt(perform[0].length()));
            switch (buttonText) {
                case '-': {
                    calculate = equals(val1, val2, operator);
                    result = decimalFormat.format(calculate) + "-";
                    flag1 = false;
                    break;
                }
                case '+': {
                    calculate = equals(val1, val2, operator);
                    result = decimalFormat.format(calculate) + "+";
                    flag1 = false;
                    break;
                }
                case '*': {
                    calculate = equals(val1, val2, operator);
                    result = decimalFormat.format(calculate) + "*";
                    flag1 = false;
                    break;
                }
                case '/': {
                    calculate = equals(val1, val2, operator);
                    result = decimalFormat.format(calculate) + "/";
                    flag1 = false;
                    break;
                }
                case '%': {
                    calculate = equals(val1, val2, operator);
                    if(calculate > 0.1)
                        calculate = calculate/100;
                    else
                        calculate = 0;
                    result = decimalFormat.format(calculate) + "";
                    flag1 = false;
                    break;
                }

            }
            if(buttonText == '='){
                    calculate = equals(val1, val2, operator);
                    result = decimalFormat.format(calculate) + "";
            }
        }
        if(buttonText == '=' && perform.length < 2){
                result = result.substring(0,result.length()-1);
            }
        if (buttonText == '%'&& perform.length < 2) {
            val1 = Float.parseFloat(perform[0]);
            if (val1 > 0.1)
                val1 = val1/100;
            else
                val1 = 0;
            result = decimalFormat.format(val1) + "";
        }
        textView.setText(result);
    }

    public float equals(float val1, float val2, String opr) {
        switch (opr) {
            case "+":
                return val1 + val2;
            case "-":
                return val1 - val2;
            case "*":
                return val1 * val2;
            case "/":
                return val1 / val2;
            default:
                return 0;
        }
    }
    public float equals(float val1, float val2, String opr1,char opr2) {
        switch (opr1) {
            case "+":
                return -val1 + val2;
            case "-":
                return -val1 - val2;
            case "*":
                return -val1 * val2;
            case "/":
                return -val1 / val2;
            default:
                return 0;
        }
    }

}