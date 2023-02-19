package ramble.sokol.svetozavr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.button.MaterialButton;

import ramble.sokol.svetozavr.R;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{

    private MaterialButton buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init(){
        buttonNext = findViewById(R.id.buttonWelcomeNext);
        buttonNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(WelcomeActivity.this, RegistrationActivity.class);
        startActivity(intent);
        finish();
    }
}