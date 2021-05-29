package com.example.electroniccommunicationhandbook.ui.student.ConfirmationRequest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.electroniccommunicationhandbook.R;
import com.example.electroniccommunicationhandbook.ViewModel.ConfirmationRequestViewModel;
import com.example.electroniccommunicationhandbook.entity.ConfirmationPaper;
import com.example.electroniccommunicationhandbook.entity.Student;
import com.example.electroniccommunicationhandbook.entity.Student_ConfirmationPaper;
import com.example.electroniccommunicationhandbook.util.UserLocalStore;

import java.util.Calendar;

import static com.example.electroniccommunicationhandbook.util.Comon.CODE_SUCCESSFUL;

public class NewRequestFragment extends Fragment {
    Button btnNewRequest;
    RadioButton radConfirmationTranscript;
    RadioButton radConfirmationStudent;
    Student student;
    int id_Confirmation_Student=26;
    int id_Confirmation_Transcript=27;
    ConfirmationRequestViewModel confirmationRequestViewModel;
    public NewRequestFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        confirmationRequestViewModel= ViewModelProviders.of(this).get(ConfirmationRequestViewModel.class);
        btnNewRequest= view.findViewById(R.id.btnSendNewRequest);
        radConfirmationStudent= view.findViewById(R.id.rdGetConfirmStudent);
        radConfirmationTranscript= view.findViewById(R.id.rdGetTranscript);
        UserLocalStore userLocalStore= new UserLocalStore(getContext());
        student= userLocalStore.getStudentLocal();
        btnNewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student_ConfirmationPaper newConfirmation= new Student_ConfirmationPaper();
                ConfirmationPaper categoryPaper;
                if(radConfirmationStudent.isChecked()){
                    categoryPaper= new ConfirmationPaper(id_Confirmation_Student);
                }
                else {
                    categoryPaper= new ConfirmationPaper(id_Confirmation_Transcript);
                }
                 Student_ConfirmationPaper student_confirmationPaper=
                         new Student_ConfirmationPaper(Calendar.getInstance().getTime(), student, categoryPaper);
                //TODO: send request
               int code =confirmationRequestViewModel.createConfirmation(student_confirmationPaper);
               if(code== CODE_SUCCESSFUL){
                   Navigation.findNavController(view).navigate(R.id.action_newRequestFragment_to_successfulRequestFragment3);
               }
               else {
                   Navigation.findNavController(view).navigate(R.id.action_newRequestFragment_to_failureRequestFragment);
               }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_request, container, false);

    }
}