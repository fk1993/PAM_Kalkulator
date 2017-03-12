package pam.kalkulator;

import static pam.kalkulator.Operation.*;

public class KalkulatorController {

    private double leftOp, rightOp, op;
    private Operation operation;

    public void setLeftOperand(double num){
        leftOp = num;
    }
    public void setRightOperand(double num){
        rightOp = num;
    }
    public void setOperand(double num){
        op = num;
    }

    public void setOperation(Operation operation){
        this.operation = operation;
    }

    public double getResult(){
        if (operation == PLUS || operation == MINUS || operation == MULT || operation == DIV || operation == POW)
            return operation.apply(leftOp, rightOp);
        else
            return operation.apply(op);
    }
}
