
package ramble.sokol.svetozavr.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import ramble.sokol.svetozavr.R;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3500;
    private Animation topAnim, botAnim;
    private ImageView imageSplash;
    private TextView textSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init(){
        topAnim = AnimationUtils.loadAnimation(this, R.anim.splash_screen_animation_top);
        botAnim = AnimationUtils.loadAnimation(this, R.anim.splash_screen_animation_bottom);
        imageSplash = findViewById(R.id.image_splash_screen);
        textSplash = findViewById(R.id.text_splash_screen);
        imageSplash.setAnimation(botAnim);
        textSplash.setAnimation(topAnim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }



}