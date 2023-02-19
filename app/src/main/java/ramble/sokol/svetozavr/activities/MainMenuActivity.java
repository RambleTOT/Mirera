package ramble.sokol.svetozavr.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import ramble.sokol.svetozavr.R;
import ramble.sokol.svetozavr.fragments.AccountFragment;
import ramble.sokol.svetozavr.fragments.CamerasFragment;
import ramble.sokol.svetozavr.fragments.SettingsFragment;

public class MainMenuActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private CamerasFragment camerasFragment;
    private SettingsFragment settingsFragment;
    private AccountFragment accountFragment;
    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init(){
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        camerasFragment = new CamerasFragment();
        settingsFragment = new SettingsFragment();
        accountFragment = new AccountFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_main_menu, camerasFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_bonus:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_main_menu, camerasFragment).commit();
                        return true;
                    case R.id.menu_message:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_main_menu, settingsFragment).commit();
                        return true;
                    case R.id.menu_startup:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_main_menu, accountFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else{
            backToast = Toast.makeText(getBaseContext(), "Нажмите ещё раз, чтобы выйти", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

}