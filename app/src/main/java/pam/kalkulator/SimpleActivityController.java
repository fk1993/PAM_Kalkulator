package pam.kalkulator;

import android.view.View;
import android.widget.*;

public class SimpleActivityController {
    protected Button menuButton, clearButton, bkspButton, signButton, ptButton,
            plusButton, minusButton, multButton, divButton, eqButton;
    protected Button[] digits;
    protected TextView display;
    protected final CalculatorController controller = new CalculatorController();
    protected boolean clr = true, pt = false;

    protected void setOnClickListeners(){
        for(int i = 0; i < digits.length; i++){
            setDigitButton(digits[i], i);
        }
        setPtButton();
        setOperationButtons();
        setClearButton();
        setBkspButton();
        setMenuButton();
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
    private void setPtButton(){
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
    }

    private void setClearButton(){
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText("0");
                pt = false;
            }
        });
    }
    private void setBkspButton(){
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
    }
    private void setMenuButton(){
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void setOperationButtons(){
        setOperationButton(plusButton, Operation.PLUS);
        setOperationButton(minusButton, Operation.MINUS);
        setOperationButton(multButton, Operation.MULT);
        setOperationButton(divButton, Operation.DIV);
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
