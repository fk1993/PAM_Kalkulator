package pam.kalkulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MenuActivity extends AppCompatActivity {

    private Button simpleButton, advancedButton, aboutButton, exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        setButtons();
    }

    private void setButtons(){
        simpleButton = (Button) findViewById(R.id.simple_button);
        setButton(simpleButton, SimpleActivity.class);

        advancedButton = (Button) findViewById(R.id.advanced_button);
        setButton(advancedButton, AdvancedActivity.class);

        aboutButton = (Button) findViewById(R.id.about_button);
        setButton(aboutButton, AboutActivity.class);

        exitButton = (Button) findViewById(R.id.exit_button);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
            }
        });
    }
    private void setButton(Button b, final Class<?> activity){
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, activity);
                startActivity(intent);
            }
        });
    }
}
