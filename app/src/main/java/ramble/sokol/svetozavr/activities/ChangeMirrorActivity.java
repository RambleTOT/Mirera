package ramble.sokol.svetozavr.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.button.MaterialButton;

import java.util.HashMap;
import java.util.Map;

import ramble.sokol.svetozavr.R;

public class ChangeMirrorActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnPreparedListener, SurfaceHolder.Callback, View.OnTouchListener{

    final static String USERNAME = "admin";
    final static String PASSWORD = "camera";
    final static String RTSP_URL = "rtsp://admin:plotina123@192.168.1.114:554/cam/realmonitor?channel=1&subtype=0";

    private MediaPlayer _mediaPlayer;
    private SurfaceHolder _surfaceHolder, _surfaceHolder2;
    private SurfaceView surfaceView, surfaceView2;
    private Canvas canvas;
    private Paint paint;
    private double xl = 0, xr = 0, yt = 0, yb = 0;
    private MaterialButton buttonMirror;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_mirror);
        init();
    }

    private void init(){
        back = findViewById(R.id.imageButtonBackChangeMirror);
        back.setOnClickListener(this);
        buttonMirror = findViewById(R.id.buttonMirrorChange);
        buttonMirror.setOnClickListener(this);
        surfaceView =
                (SurfaceView) findViewById(R.id.surfaceChanger);
        surfaceView.setOnTouchListener(this);
        _surfaceHolder = surfaceView.getHolder();
        _surfaceHolder.addCallback(this);
        _surfaceHolder.setFixedSize(320, 240);
        surfaceView2 = (SurfaceView)findViewById(R.id.surfaceDrawChange);
        _surfaceHolder2 = surfaceView2.getHolder();
        _surfaceHolder2.setFormat(PixelFormat.TRANSPARENT);
        _surfaceHolder2.addCallback(this);
        _surfaceHolder2.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceView2.setZOrderMediaOverlay(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageButtonBackChangeMirror:
            case R.id.buttonMirrorChange:
                onBackPressed();
                break;
        }
    }

    private void drawFocusRect(double RectLeft, double RectTop, double RectRight, double RectBottom, int color) {
        canvas = _surfaceHolder2.lockCanvas();
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        //border's properties
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setStrokeWidth(7);
        canvas.drawRect((float) RectLeft, (float) RectTop, (float) RectRight, (float)RectBottom, paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.trans));
        canvas.drawRect((float) RectLeft, (float) RectTop, (float) RectRight, (float)RectBottom, paint);
        _surfaceHolder2.unlockCanvasAndPost(canvas);
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        _mediaPlayer.start();
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        _mediaPlayer = new MediaPlayer();
        _mediaPlayer.setDisplay(_surfaceHolder);
        Context context = getApplicationContext();
        Map<String, String> headers = getRtspHeaders();
        Uri source = Uri.parse(RTSP_URL);

        try {
            _mediaPlayer.setDataSource(context, source, headers);
            _mediaPlayer.setOnPreparedListener(this);
            _mediaPlayer.prepareAsync();
        }
        catch (Exception e) {}
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        _mediaPlayer.release();
    }

    private Map<String, String> getRtspHeaders() {
        Map<String, String> headers = new HashMap<>();
        String basicAuthValue = getBasicAuthValue(USERNAME, PASSWORD);
        headers.put("Authorization", basicAuthValue);
        return headers;
    }

    private String getBasicAuthValue(String usr, String pwd) {
        String credentials = usr + ":" + pwd;
        int flags = Base64.URL_SAFE | Base64.NO_WRAP;
        byte[] bytes = credentials.getBytes();
        return "Basic " + Base64.encodeToString(bytes, flags);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xl = motionEvent.getX();
                yt = motionEvent.getY();
                if (xl < 0){
                    xl = 0;
                }
                if (yt < 0){
                    yt = 0;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                xr = motionEvent.getX();
                yb = motionEvent.getY();
                drawFocusRect(xl , yt , xr , yb, getResources().getColor(R.color.color_main));
                break;
            case MotionEvent.ACTION_UP:
                xr = motionEvent.getX();
                yb = motionEvent.getY();
                if (xr < 0){
                    xr = 0;
                }
                if (yb < 0){
                    yb = 0;
                }
                drawFocusRect(xl , yt , xr , yb, getResources().getColor(R.color.color_main));
                buttonMirror.setVisibility(View.VISIBLE);
                break;
            case MotionEvent.ACTION_CANCEL:
        }

        return true;
    }

    private void saveCoordinates(){
        SharedPreferences sPref = getSharedPreferences("saveXL", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putFloat("XLSave", (float) xl);
        editor.commit();
        SharedPreferences sPref2 = getSharedPreferences("saveYT", MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sPref2.edit();
        editor2.putFloat("YTSave", (float) yt);
        editor2.commit();
        SharedPreferences sPref3 = getSharedPreferences("saveXR", MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sPref3.edit();
        editor3.putFloat("XRSave", (float) xr);
        editor3.commit();
        SharedPreferences sPref4 = getSharedPreferences("saveYB", MODE_PRIVATE);
        SharedPreferences.Editor editor4 = sPref4.edit();
        editor4.putFloat("YBSave", (float) yb);
        editor4.commit();
    }
}