package pam.kalkulator;

public class KalkulatorController {

    private double leftOp, rightOp, op, ans;
    private int operation;

    public void setLeftOperand(String s){
        leftOp = Double.parseDouble(s);
    }
    public void setRightOperand(String s){
        rightOp = Double.parseDouble(s);
    }
    public void setOperation(int operation){
        this.operation = operation;
    }
    public double getResult(){
        switch (operation){
            case Operation.PLUS:
                return leftOp + rightOp;
            case Operation.MINUS:
                return leftOp - rightOp;
            case Operation.MULT:
                return leftOp * rightOp;
            case Operation.DIV:
                return leftOp / rightOp;
            case Operation.SQRT:
                return Math.sqrt(op);
            case Operation.SQ:
                return op * op;
            case Operation.POW:
                return Math.pow(leftOp, rightOp);
            case Operation.LOG:
                return Math.log10(op);
            case Operation.SIN:
                return Math.sin(op);
            case Operation.COS:
                return Math.cos(op);
            case Operation.TAN:
                return Math.tan(op);
            case Operation.LN:
                return Math.log(op);
            default:
                throw new IllegalArgumentException();
        }
    }
}
