package pam.kalkulator;

import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.*;
import android.content.Intent;
import static java.lang.Double.*;

public class SimpleActivityController {

    protected AppCompatActivity activity;
    protected Button menuButton, clearButton, bkspButton, signButton, ptButton,
            plusButton, minusButton, multButton, divButton, eqButton;
    protected Button[] digits;
    protected TextView display;
    protected final CalculatorController controller = new CalculatorController();
    protected boolean clr = true, pt = false;
    protected int operationIndex;
    protected double leftOp;

    public void setActivity(AppCompatActivity activity){
        this.activity = activity;
    }

    protected void setOperation(int index){
        controller.setOperation(Operation.getOperation(index));
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

    protected boolean getClr() {
        return clr;
    }
    protected void setClr(boolean clr) {
        this.clr = clr;
    }

    protected boolean getPt() {
        return pt;
    }
    protected void setPt(boolean pt) {
        this.pt = pt;
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
                if (!pt) {
                    display.append(".");
                    pt = true;
                    clr = false;
                }
            }
        });
    }

    private void setClearButton(){
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText("0");
                clr = true;
                pt = false;
            }
        });
    }
    private void setBkspButton(){
        bkspButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = display.getText().length();
                CharSequence text = display.getText();
                if (length == 1 || (length == 2 && text.charAt(0) == '-') || clr == true){
                    display.setText("0");
                    clr = true;
                } else {
                    if (text.charAt(length - 1) == '.')
                        pt = false;
                    display.setText(text.subSequence(0, length - 1));
                }
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
                CharSequence text = display.getText();
                if (text.charAt(0) == '-')
                    display.setText(text.subSequence(1, text.length()));
                else
                    display.setText("-" + text);
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
                clr = true;
                pt = true;
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
                    operationIndex = Operation.getOperationIndex(operation);
                } catch(NumberFormatException e){
                    viewErrorMessage();
                }
                clr = true;
                pt = true;
            }
        });
    }

    protected void clearDisplay(){
        if (clr == true) {
            display.setText("");
            clr = false;
            pt = false;
        }
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
