package pam.kalkulator;

import java.util.*;

public abstract class Operation {

    public static final Operation PLUS = new Operation(){
        @Override
        public double apply(double leftOp, double rightOp){
            return leftOp + rightOp;
        }
    };
    public static final Operation MINUS = new Operation(){
        @Override
        public double apply(double leftOp, double rightOp){
            return leftOp - rightOp;
        }
    };
    public static final Operation MULT = new Operation(){
        @Override
        public double apply(double leftOp, double rightOp){
            return leftOp * rightOp;
        }
    };
    public static final Operation DIV = new Operation(){
        @Override
        public double apply(double leftOp, double rightOp){
            return leftOp / rightOp;
        }
    };
    public static final Operation SQRT = new Operation(){
        @Override
        public double apply(double op){
            return Math.sqrt(op);
        }
    };
    public static final Operation SQ = new Operation(){
        @Override
        public double apply(double op){
            return op * op;
        }
    };
    public static final Operation POW = new Operation(){
        @Override
        public double apply(double leftOp, double rightOp){
            return Math.pow(leftOp, rightOp);
        }
    };
    public static final Operation LOG = new Operation(){
        @Override
        public double apply(double op){
            return Math.log10(op);
        }
    };
    public static final Operation SIN = new Operation(){
        @Override
        public double apply(double op){
            return Math.sin(op);
        }
    };
    public static final Operation COS = new Operation(){
        @Override
        public double apply(double op){
            return Math.cos(op);
        }
    };
    public static final Operation TAN = new Operation(){
        @Override
        public double apply(double op){
            return Math.tan(op);
        }
    };
    public static final Operation LN = new Operation(){
        @Override
        public double apply(double op){
            return Math.log(op);
        }
    };

    private static final List<Operation> OPERATIONS = Arrays.asList(
            PLUS, MINUS, MULT, DIV, SQRT, SQ, POW, LOG, SIN, COS, TAN, LN);

    public static int getOperationIndex(Operation operation){
        return OPERATIONS.indexOf(operation);
    }
    public static Operation getOperation(int index){
        return OPERATIONS.get(index);
    }

    public double apply(double leftOp, double rightOp){
        return 0;
    }
    public double apply(double op){
        return 0;
    }
}
