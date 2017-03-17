package pam.kalkulator;

import android.view.View;
import android.widget.Button;

public class AdvancedActivityController extends SimpleActivityController {
    protected Button sqrtButton, sqButton, powButton, logButton,
            sinButton, cosButton, tanButton, lnButton;

    @Override
    protected void setOnClickListeners(){
        super.setOnClickListeners();
        setOperationButton(sqrtButton, Operation.SQRT);
        setOperationButton(sqButton, Operation.SQ);
        setOperationButton(logButton, Operation.LOG);
        setOperationButton(sinButton, Operation.SIN);
        setOperationButton(cosButton, Operation.COS);
        setOperationButton(tanButton, Operation.TAN);
        setOperationButton(lnButton, Operation.LN);
        powButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.setLeftOperand(getNumber());
                controller.setOperation(Operation.POW);
                reset();
            }
        });
    }

    private void setOperationButton(Button b, final Operation operation){
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.setOperand(getNumber());
                controller.setOperation(operation);
                viewResult();
                reset();
            }
        });
    }
}
