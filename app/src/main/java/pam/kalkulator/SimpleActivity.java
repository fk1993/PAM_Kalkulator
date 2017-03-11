package pam.kalkulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class SimpleActivity extends AppCompatActivity {

    private Button menuButton, clearButton, bkspButton, signButton, ptButton,
            plusButton, minusButton, multButton, divButton, eqButton;
    private Button[] digits;
    private TextView display;
    private KalkulatorController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple);
    }
}
