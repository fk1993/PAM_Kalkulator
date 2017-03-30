package pam.kalkulator;

import android.view.View;
import android.widget.Button;

public class AdvancedActivityController extends SimpleActivityController {

    protected Button sqrtButton, sqButton, powButton, logButton,
            sinButton, cosButton, tanButton, lnButton;

    @Override
    protected void setOnClickListeners(){
        super.setOnClickListeners();
        setAdvancedOperationButton(sqrtButton, Operation.SQRT);
        setAdvancedOperationButton(sqButton, Operation.SQ);
        setAdvancedOperationButton(logButton, Operation.LOG);
        setAdvancedOperationButton(sinButton, Operation.SIN);
        setAdvancedOperationButton(cosButton, Operation.COS);
        setAdvancedOperationButton(tanButton, Operation.TAN);
        setAdvancedOperationButton(lnButton, Operation.LN);
        setOperationButton(powButton, Operation.POW);
    }

    protected void setAdvancedOperationButton(Button b, final Operation operation){
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    controller.setOperand(getNumber());
                    controller.setOperation(operation);
                    viewResult();
                } catch(NumberFormatException e){
                    viewErrorMessage();
                }
                reset();
            }
        });
    }
}
