package ramble.sokol.svetozavr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

import ramble.sokol.svetozavr.R;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private EditText editIP, editName, editPassword;
    private MaterialButton buttonRegistration;
    private String ip, name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init(){
        editIP = findViewById(R.id.editRegIp);
        editName = findViewById(R.id.editRegName);
        editPassword = findViewById(R.id.editRegPassword);
        buttonRegistration = findViewById(R.id.buttonRegistration);
        buttonRegistration.setOnClickListener(this);
        editIP.setOnFocusChangeListener(this);
        editName.setOnFocusChangeListener(this);
        editPassword.setOnFocusChangeListener(this);
    }

    private void processingEditText(){
        ip = editIP.getText().toString();
        name = editName.getText().toString();
        password = editPassword.getText().toString();
        if (ip.isEmpty()){
            editIP.setBackgroundResource(R.drawable.edit_text_background_error);
        }
        if (name.isEmpty()){
            editName.setBackgroundResource(R.drawable.edit_text_background_error);
        }
        if (password.isEmpty()){
            editPassword.setBackgroundResource(R.drawable.edit_text_background_error);
        }
        if (!(name.isEmpty()) && !(ip.isEmpty()) && !(password.isEmpty())){
            editIP.setBackgroundResource(R.drawable.edit_text_background);
            editName.setBackgroundResource(R.drawable.edit_text_background);
            editPassword.setBackgroundResource(R.drawable.edit_text_background);
            Intent intent = new Intent(RegistrationActivity.this, MirrorEditorActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        processingEditText();
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()){
            case R.id.editRegIp:
                editIP.setBackgroundResource(R.drawable.edit_text_background);
                editName.setBackgroundResource(R.drawable.edit_text_background);
                editPassword.setBackgroundResource(R.drawable.edit_text_background);
            case R.id.editRegName:
                editIP.setBackgroundResource(R.drawable.edit_text_background);
                editName.setBackgroundResource(R.drawable.edit_text_background);
                editPassword.setBackgroundResource(R.drawable.edit_text_background);
            case R.id.editRegPassword:
                editIP.setBackgroundResource(R.drawable.edit_text_background);
                editName.setBackgroundResource(R.drawable.edit_text_background);
                editPassword.setBackgroundResource(R.drawable.edit_text_background);
        }
    }
}