package ramble.sokol.svetozavr.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import ramble.sokol.svetozavr.activities.ChangeCameraActivity;
import ramble.sokol.svetozavr.activities.ChangeMirrorActivity;
import ramble.sokol.svetozavr.R;

public class SettingsFragment extends Fragment implements View.OnClickListener{

    private CardView camera, mirror, data, info, help;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    private void init(){
        camera = getActivity().findViewById(R.id.layoutChangeCamera);
        camera.setOnClickListener(this);
        mirror = getActivity().findViewById(R.id.layoutChangeMirror);
        mirror.setOnClickListener(this);
        data = getActivity().findViewById(R.id.layoutChangeData);
        data.setOnClickListener(this);
        info = getActivity().findViewById(R.id.layoutInformation);
        info.setOnClickListener(this);
        help = getActivity().findViewById(R.id.layoutHelp);
        help.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.layoutChangeCamera:
                camera.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_click_image));
                Intent intent = new Intent(getActivity(), ChangeCameraActivity.class);
                startActivity(intent);
                break;
            case R.id.layoutChangeMirror:
                mirror.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_click_image));
                Intent intent2 = new Intent(getActivity(), ChangeMirrorActivity.class);
                startActivity(intent2);
                break;
        }
    }
}

