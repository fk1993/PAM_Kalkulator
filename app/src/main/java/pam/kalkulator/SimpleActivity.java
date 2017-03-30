package pam.kalkulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class SimpleActivity extends AppCompatActivity {

    private final SimpleActivityController controller = new SimpleActivityController();
    private final static String DISP = "pam.kalkulator.disp";
    private final static String OPERATION = "pam.kalkulator.operation";
    private final static String LEFT_OP = "pam.kalkulator.leftOp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple);
        findView();
        controller.setActivity(this);
        controller.setOnClickListeners();
        if (savedInstanceState != null) {
            controller.display.setText(savedInstanceState.getCharSequence(DISP));
            controller.setOperation(savedInstanceState.getInt(OPERATION));
            controller.setLeftOp(savedInstanceState.getDouble(LEFT_OP));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle state){
        super.onSaveInstanceState(state);
        state.putCharSequence(DISP, controller.display.getText());
        state.putInt(OPERATION, controller.getOperationIndex());
        state.putDouble(LEFT_OP, controller.getLeftOp());
    }

    private void findView(){
        controller.display = (TextView) findViewById(R.id.disp);
        controller.menuButton = (Button) findViewById(R.id.menu_button);
        controller.clearButton = (Button) findViewById(R.id.clear_button);
        controller.bkspButton = (Button) findViewById(R.id.bksp_button);
        controller.ptButton = (Button) findViewById(R.id.pt_button);
        controller.signButton = (Button) findViewById(R.id.sign_button);
        controller.plusButton = (Button) findViewById(R.id.plus_button);
        controller.minusButton = (Button) findViewById(R.id.minus_button);
        controller.multButton = (Button) findViewById(R.id.mult_button);
        controller.divButton = (Button) findViewById(R.id.div_button);
        controller.eqButton = (Button) findViewById(R.id.eq_button);
        findDigits();
    }

    private void findDigits(){
        controller.digits = new Button[10];
        controller.digits[0] = (Button) findViewById(R.id.button0);
        controller.digits[1] = (Button) findViewById(R.id.button1);
        controller.digits[2] = (Button) findViewById(R.id.button2);
        controller.digits[3] = (Button) findViewById(R.id.button3);
        controller.digits[4] = (Button) findViewById(R.id.button4);
        controller.digits[5] = (Button) findViewById(R.id.button5);
        controller.digits[6] = (Button) findViewById(R.id.button6);
        controller.digits[7] = (Button) findViewById(R.id.button7);
        controller.digits[8] = (Button) findViewById(R.id.button8);
        controller.digits[9] = (Button) findViewById(R.id.button9);
    }
}
