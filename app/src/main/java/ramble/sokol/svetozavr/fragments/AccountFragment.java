package ramble.sokol.svetozavr.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ramble.sokol.svetozavr.R;
import ramble.sokol.svetozavr.activities.WelcomeActivity;

public class AccountFragment extends Fragment implements View.OnClickListener{

    private TextView textExit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        textExit = getActivity().findViewById(R.id.textButtonAccountExit);
        textExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textButtonAccountExit:
                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                startActivity(intent);
                break;
        }
    }
}