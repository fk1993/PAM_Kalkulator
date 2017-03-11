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
    public double getResult(){
        switch (operation){
            case Operation.PLUS:
                ans = leftOp + rightOp;
                break;
            case Operation.MINUS:
                ans = leftOp - rightOp;
                break;
            case Operation.MULT:
                ans = leftOp * rightOp;
                break;
            case Operation.DIV:
                ans = leftOp / rightOp;
                break;
            case Operation.SQRT:
                ans = Math.sqrt(op);
                break;
            case Operation.SQ:
                ans = op * op;
                break;
            case Operation.POW:
                ans = Math.pow(leftOp, rightOp);
                break;
            case Operation.LOG:
                ans = Math.log10(op);
                break;
            case Operation.SIN:
                ans = Math.sin(op);
                break;
            case Operation.COS:
                ans = Math.cos(op);
                break;
            case Operation.TAN:
                ans = Math.tan(op);
                break;
            case Operation.LN:
                ans = Math.log(op);
                break;
        }
        return ans;
    }
}
