package com.example.electroniccommunicationhandbook.ui.student.ConfirmationRequest;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.electroniccommunicationhandbook.MainActivity;
import com.example.electroniccommunicationhandbook.R;


public class FailureRequestFragment extends Fragment {

    Button btnGoToHome, btnGoNewRequest, btnBack;
    public FailureRequestFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnGoToHome= view.findViewById(R.id.btnFailureToHome);
        btnGoNewRequest= view.findViewById(R.id.btnRetryRequest);
        btnBack= view.findViewById(R.id.btn_error_to_new);
        btnGoToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnGoNewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {Navigation.findNavController(v).navigate(R.id.action_failureRequestFragment_to_newRequestFragment);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_failureRequestFragment_to_boardConfirmation);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_failure_request, container, false);
    }
}