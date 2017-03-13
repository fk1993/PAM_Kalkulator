package pam.kalkulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class SimpleActivity extends AppCompatActivity {

    private Button menuButton, clearButton, bkspButton, signButton, ptButton,
            plusButton, minusButton, multButton, divButton, eqButton;
    private Button[] digits;
    private TextView display;
    private final CalculatorController controller = new CalculatorController();
    private boolean clr = true, pt = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple);

        display = (TextView) findViewById(R.id.disp);

        menuButton = (Button) findViewById(R.id.menu_button);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        clearButton = (Button) findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText("0");
                pt = false;
            }
        });

        bkspButton = (Button) findViewById(R.id.bksp_button);
        bkspButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = display.getText().length();
                if (length > 1)
                    display.getEditableText().delete(length - 1, length);
                else
                    display.setText("0");
            }
        });

        ptButton = (Button) findViewById(R.id.pt_button);
        ptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clr = false;
                if (!pt) {
                    display.append(".");
                    pt = true;
                }
            }
        });

        setDigits();
        setOperationButtons();
    }

    private void setOperationButtons(){
        signButton = (Button) findViewById(R.id.sign_button);
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence number = display.getText();
                if (number.charAt(0) == '-')
                    display.getEditableText().delete(0, 1);
                else
                    display.setText("-" + number);
            }
        });

        plusButton = (Button) findViewById(R.id.plus_button);
        setOperationButton(plusButton, Operation.PLUS);

        minusButton = (Button) findViewById(R.id.minus_button);
        setOperationButton(minusButton, Operation.MINUS);

        multButton = (Button) findViewById(R.id.mult_button);
        setOperationButton(multButton, Operation.MULT);

        divButton = (Button) findViewById(R.id.div_button);
        setOperationButton(divButton, Operation.DIV);

        eqButton = (Button) findViewById(R.id.eq_button);
        eqButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                controller.setRightOperand(getNumber());
                double ans = controller.getResult();
                display.setText(Double.toString(ans));
                clr = true;
                pt = false;
            }
        });
    }
    private void setOperationButton(Button b, final Operation operation){
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.setLeftOperand(getNumber());
                controller.setOperation(operation);
                clr = true;
                pt = false;
            }
        });
    }

    private void setDigits(){
        digits = new Button[10];
        digits[0] = (Button) findViewById(R.id.button0);
        digits[1] = (Button) findViewById(R.id.button1);
        digits[2] = (Button) findViewById(R.id.button2);
        digits[3] = (Button) findViewById(R.id.button3);
        digits[4] = (Button) findViewById(R.id.button4);
        digits[5] = (Button) findViewById(R.id.button5);
        digits[6] = (Button) findViewById(R.id.button6);
        digits[7] = (Button) findViewById(R.id.button7);
        digits[8] = (Button) findViewById(R.id.button8);
        digits[9] = (Button) findViewById(R.id.button9);

        for(int i = 0; i < digits.length; i++){
            setDigitButton(digits[i], i);
        }
    }
    private void setDigitButton(Button b, final int digit){
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearDisplay();
                display.append(Integer.toString(digit));
            }
        });
    }

    private void clearDisplay(){
        if (clr == true) {
            display.setText("");
            clr = false;
        }
    }

    private double getNumber(){
        return Double.parseDouble(display.getText().toString());
    }
}
