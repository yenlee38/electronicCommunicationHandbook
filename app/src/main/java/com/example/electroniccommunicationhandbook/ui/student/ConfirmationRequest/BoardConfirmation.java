package com.example.electroniccommunicationhandbook.ui.student.ConfirmationRequest;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.electroniccommunicationhandbook.MainActivity;
import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.ViewModel.ConfirmationRequestViewModel;
import com.example.electroniccommunicationhandbook.entity.ConfirmationPaper;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_ConfirmationPaper;
import com.example.electroniccommunicationhandbook.repository.MainRepository;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class BoardConfirmation extends Fragment {
    AppCompatButton btnNewRequest;
    RecyclerView recyclerViewRequire;
    ArrayList<Student_ConfirmationPaper> historiesRequest;
    AppCompatButton btnBack;
    ConfirmationRequestViewModel confirmationRequestViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      //  confirmationRequestViewModel= new ViewModelProvider(this).get(ConfirmationRequestViewModel.class);
        confirmationRequestViewModel= ViewModelProviders.of(this).get(ConfirmationRequestViewModel.class);
        btnNewRequest= view.findViewById(R.id.btnNewRequest);
        btnBack= view.findViewById(R.id.btn_boardrequest_home);
        recyclerViewRequire= getView().findViewById(R.id.rcvRequestConfirmation1);
        historiesRequest= new ArrayList<>();
        UserLocalStore userLocalStore= new UserLocalStore(getContext());
        Student student = userLocalStore.getStudentLocal();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        //confirmationRequestViewModel.
        confirmationRequestViewModel.findAllConfirmation(student.getStudentId()).observe(getViewLifecycleOwner(),
                new Observer<ArrayList<Student_ConfirmationPaper>>() {
                    @Override
                    public void onChanged(ArrayList<Student_ConfirmationPaper> student_confirmationPapers) {
                        if(student_confirmationPapers != null){
                            Log.e("onChange",student_confirmationPapers.toString());
                            historiesRequest=student_confirmationPapers;
                            recyclerViewRequire.setLayoutManager(new LinearLayoutManager(getContext()));
                            //  requestHistory.add(new Student_ConfirmationPaper(Calendar.getInstance().getTime(),student, confirmationPaperA ));

                            ComfirmationAdapter adapter= new ComfirmationAdapter(historiesRequest);
                            recyclerViewRequire.setAdapter(adapter);
                        }
                    }
                });
        btnNewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_boardConfirmation_to_newRequestFragment);
                Log.e("bt","OK");

            }
        });


        ConfirmationPaper confirmationPaperA= new ConfirmationPaper(1,"Xác nhận sinh viên");
        ConfirmationPaper confirmationPaperB= new ConfirmationPaper(2,"Xác nhận Điểm");

      // List<Student_ConfirmationPaper> requestHistory= new ArrayList<>();
      //  requestHistory.add(new Student_ConfirmationPaper(Calendar.getInstance().getTime(),student, confirmationPaperA ));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_board_confirmation, container, false);
    }
}