package ramble.sokol.svetozavr.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import ramble.sokol.svetozavr.R;

public class CamerasFragment extends Fragment implements MediaPlayer.OnPreparedListener, SurfaceHolder.Callback{

    final static String USERNAME = "admin";
    final static String PASSWORD = "camera";
    final static String RTSP_URL = "rtsp://admin:plotina123@192.168.1.114:554/cam/realmonitor?channel=1&subtype=0";

    private MediaPlayer _mediaPlayer;
    private SurfaceHolder _surfaceHolder, _surfaceHolder2;
    private SurfaceView main, second;
    private double xl = 0, xr = 0, yt = 0, yb = 0;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        main = getActivity().findViewById(R.id.mainCamera);
        _surfaceHolder = main.getHolder();
        _surfaceHolder.addCallback(this);
        _surfaceHolder.setFixedSize(320, 240);
        getCoordinates();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cameras, container, false);
    }

    private void getCoordinates(){
        SharedPreferences sPref1 = getActivity().getSharedPreferences("saveXL", getActivity().MODE_PRIVATE);
        xl = sPref1.getFloat("XLSave", 0);
        SharedPreferences sPref2 = getActivity().getSharedPreferences("saveYT", getActivity().MODE_PRIVATE);
        yt = sPref2.getFloat("YTSave", 0);
        SharedPreferences sPref3 = getActivity().getSharedPreferences("saveXR", getActivity().MODE_PRIVATE);
        xr = sPref3.getFloat("XRSave", 0);
        SharedPreferences sPref4 = getActivity().getSharedPreferences("saveYB", getActivity().MODE_PRIVATE);
        yb = sPref4.getFloat("YBSave", 0);
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        _mediaPlayer.start();
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        _mediaPlayer = new MediaPlayer();
        _mediaPlayer.setDisplay(_surfaceHolder);

        Context context = getContext();
        Map<String, String> headers = getRtspHeaders();
        Uri source = Uri.parse(RTSP_URL);

        try {
            // Specify the IP camera's URL and auth headers.
            _mediaPlayer.setDataSource(context, source, headers);

            // Begin the process of setting up a video stream.
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
        Map<String, String> headers = new HashMap<String, String>();
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

}