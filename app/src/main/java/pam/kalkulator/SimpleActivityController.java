package pam.kalkulator;

import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.*;
import android.content.Intent;
import java.util.*;
import static java.lang.Double.*;

public class SimpleActivityController {

    protected AppCompatActivity activity;
    protected Button menuButton, clearButton, bkspButton, signButton, ptButton,
            plusButton, minusButton, multButton, divButton, eqButton;
    protected Button[] digits;
    protected TextView display;
    protected final CalculatorController controller = new CalculatorController();
    private boolean clr = true, pt = false;
    protected int operationIndex;
    protected double leftOp;

    protected static final List<Operation> OPERATIONS = Arrays.asList(Operation.PLUS, Operation.MINUS, Operation.MULT, Operation.DIV,
            Operation.SQRT, Operation.SQ, Operation.POW, Operation.LOG, Operation.SIN, Operation.COS, Operation.TAN, Operation.LN);

    public void setActivity(AppCompatActivity activity){
        this.activity = activity;
    }

    protected void setOperation(int index){
        controller.setOperation(OPERATIONS.get(index));
    }
    protected int getOperationIndex(){
        return operationIndex;
    }

    protected double getLeftOp(){
        return leftOp;
    }
    protected void setLeftOp(double num){
        controller.setLeftOperand(num);
    }

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
                Editable text = display.getEditableText();
                if (length > 1) {
                    if (text.charAt(length - 1) == '.')
                        pt = false;
                    text.delete(length - 1, length);
                }
                else
                    display.setText("0");
            }
        });
    }
    private void setMenuButton(){
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MenuActivity.class);
                activity.startActivity(intent);
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
                try {
                    controller.setRightOperand(getNumber());
                    viewResult();
                } catch(NumberFormatException e){
                    viewErrorMessage();
                }
                reset();
            }
        });
    }

    protected void setOperationButton(Button b, final Operation operation){
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    leftOp = getNumber();
                    controller.setLeftOperand(leftOp);
                    controller.setOperation(operation);
                    operationIndex = OPERATIONS.indexOf(operation);
                } catch(NumberFormatException e){
                    viewErrorMessage();
                }
                reset();
            }
        });
    }

    protected void clearDisplay(){
        if (clr == true) {
            display.setText("");
            clr = false;
        }
    }

    protected void reset() {
        clr = true;
        pt = false;
    }

    protected double getNumber(){
        return parseDouble(display.getText().toString());
    }

    protected void viewResult(){
        double ans = controller.getResult();
        if (isInfinite(ans) || isNaN(ans))
            viewErrorMessage();
        else
            display.setText(Double.toString(ans));
    }

    protected void viewErrorMessage(){
        Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show();
    }
}
