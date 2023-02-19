package ramble.sokol.svetozavr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import ramble.sokol.svetozavr.R;

public class ChangeCameraActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_camera);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init(){
        back = findViewById(R.id.imageButtonBackChangeCamera);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageButtonBackChangeCamera:
                onBackPressed();
                break;
        }
    }
}